package lenovo.example.com.app1103;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * author：shashe
 * 日期：2018/11/3
 */
public class App extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化
        Fresco.initialize(this);
        //数据库初始化
        SqliteUtils.getSqliteUtils().init(this);
    }
}
