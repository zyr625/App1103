package lenovo.example.com.app1103;

import android.os.Handler;
import android.os.Message;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * author：shashe
 * 日期：2018/11/3
 */
public class OkHttpUtils {
    private HttpListener listener;

    public OkHttpUtils get(String url) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                Message message = Message.obtain();
                message.obj = response.body().string();
                handler.sendMessage(message);
            }
        });
        return this;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
            listener.success(data);
        }
    };

    public void result(HttpListener listener) {
        this.listener = listener;
    }
}
