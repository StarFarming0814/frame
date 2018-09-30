package com.li.net.proxy.impl;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.NonNull;

import com.li.net.callback.ICallBack;
import com.li.net.proxy.IHttpProxy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Dispatcher;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @author Li
 * @since 2018/8/26
 */
public class OkHttpProcessor implements IHttpProxy {

    private OkHttpClient client;
    private Handler handler;
    private String downloadPath;

    public OkHttpProcessor() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)      //设置连接超时
                    .readTimeout(60, TimeUnit.SECONDS)         //设置读超时
                    .writeTimeout(60, TimeUnit.SECONDS)        //设置写超时
                    .retryOnConnectionFailure(true)                    //是否自动重连
                    .build();
        }
        if (handler == null) {
            handler = new Handler(Looper.getMainLooper());
        }
        downloadPath = Environment.getExternalStorageDirectory().getPath() + File.separator;
    }

    @Override
    public void get(String url, Map<String, String> param, Object tag, final ICallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .tag(tag)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                if (callBack != null) {
                    callBack.onFailure(e.getMessage());
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (callBack != null) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        callBack.onSuccess(body.string());
                    }
                }
            }
        });
    }

    @Override
    public void post(String url, Map<String, String> param, Object tag, final ICallBack callBack) {
        FormBody.Builder builder = new FormBody.Builder();
        if (param != null) {
            for (Object obj : param.entrySet()) {
                Map.Entry entry = (Map.Entry) obj;
                String key = entry.getKey().toString();
                String val = entry.getValue().toString();
                builder.add(key, val);
            }
        }
        Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .tag(tag)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull final IOException e) {
                if (callBack != null) {
                    callBack.onFailure(e.getMessage());
                    callBack.onFinish();
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                    if (callBack != null) {
                        ResponseBody body = response.body();
                        if (body != null) {
                            SystemClock.sleep(3_000);
                            callBack.onSuccess(body.string());
                        }
                    }
                } finally {
                    if (callBack != null) {
                        callBack.onFinish();
                    }
                }

            }
        });
    }

    @Override
    public void download(final String url, Map<String, String> param, String filePath, Object tag, final ICallBack callBack) {
        //TODO 实现下载逻辑
        Request request = new Request.Builder()
                .url(url)
                .tag(tag)
                .build();
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        final String finalFilePath = filePath;
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                if (callBack != null) {
                    callBack.onDownloadFailed();
                }

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len;
                FileOutputStream fos = null;

                String[] strings = url.split("/");
                String download = downloadPath + finalFilePath + strings[strings.length - 1];
                // 先将原来的删掉
                File file = new File(download);
                if (!file.exists() || file.delete()) {

                    try {
                        ResponseBody body = response.body();
                        if (body == null) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callBack.onDownloadFailed();
                                }
                            });
                            return;
                        }
                        is = body.byteStream();
                        long total = body.contentLength();
                        fos = new FileOutputStream(file);
                        long sum = 0;
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);
                            sum += len;
                            int progress = (int) (sum * 1.0f / total * 100);
                            // 下载中
                            callBack.onDownloading(progress);
                        }
                        fos.flush();
                        // 下载完成
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callBack.onDownloadSuccess();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                        callBack.onDownloadFailed();
                    } finally {
                        try {
                            if (is != null)
                                is.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (callBack != null) {
                                callBack.onDownloadFailed();
                            }
                        }
                    });
                }

            }
        });
    }

    @Override
    public void cancel(Object tag) {
        Dispatcher dispatcher = client.dispatcher();
        for (Call call : dispatcher.queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : dispatcher.runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }
}
