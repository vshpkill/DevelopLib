package develop.lib.http;

import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;

/**
 * Created by KKK on 2017/9/4.
 */

public class HttpControl {
    public void beginRequest(final Request request, final RequestCallBack callBack) {
        if (request == null || TextUtils.isEmpty(request.getUrl())) {
            throw new RuntimeException("请补全请求信息");
        }
        ExecutorService service = HttpThreadPool.getInstance();
        service.execute(new Runnable() {
            @Override
            public void run() {
                Log.e("返回信息","方法执行");
                buildHttp(request,callBack);
            }
        });
    }

    private void buildHttp(Request request,RequestCallBack callBack) {
        try {
            URL httpUrl = new URL(request.getUrl());
            HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestMethod(request.getMethod());
            connection.setDoOutput(true);
            connection.setDoInput(true);
            if (TextUtils.equals("POST", request.getMethod())) {
                connection.setUseCaches(false);
                OutputStream outputStream = connection.getOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(request.getPrams());
                objectOutputStream.flush();
                objectOutputStream.close();
            } else {
                connection.setUseCaches(true);
            }
            if (200 == connection.getResponseCode()) {
                InputStream inputStream = connection.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while (-1 != (len = inputStream.read(buffer))) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                callBack.onSuccess(baos.toString("utf-8"));
            } else {
                Log.e("返回信息","失败");
                callBack.onFail();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRequestMethod() {

    }
}
