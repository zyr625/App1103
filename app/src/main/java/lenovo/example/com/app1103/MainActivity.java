package lenovo.example.com.app1103;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.bgaBanner)
    BGABanner mBGABanner;
    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;
    private List<String> images = new ArrayList<>();
    private List<String> mTitle = new ArrayList<>();
    private CarAdapter carAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);//绑定ButterKnife
        doBannerData();

        carAdapter = new CarAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(carAdapter);
        if (NetworkUtils.isConnected(this)) {
            doCarData();
        } else {
            //无网获取数据库
            CacheCar car = SqliteUtils.getSqliteUtils().queryAll().get(0);
            notify(car.getData());
        }
    }

    //请求购物车
    private void doCarData() {
        String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
        new OkHttpUtils().get(url).result(new HttpListener() {
            @Override
            public void success(String data) {
                /*if (!SqliteUtils.getSqliteUtils().queryAll().isEmpty()) {
                    CacheCar cacheCar = SqliteUtils.getSqliteUtils().query("1");
                    cacheCar.setData(data);
                    //更新
                    SqliteUtils.getSqliteUtils().update(cacheCar);
                } else {
                    CacheCar cacheCar = new CacheCar();
                    cacheCar.setData(data);
                    SqliteUtils.getSqliteUtils().insert(cacheCar);
                }*/
                SqliteUtils.getSqliteUtils().deleteAll();
                CacheCar cacheCar = new CacheCar();
                cacheCar.setData(data);
                SqliteUtils.getSqliteUtils().insert(cacheCar);

                MainActivity.this.notify(data);
            }
        });
    }

    private void notify(String data) {
        CarBean carBean = new Gson().fromJson(data, CarBean.class);
        carAdapter.setList(carBean.getData());
    }

    //请求Banner数据
    private String bannerUrl = "http://www.zhaoapi.cn/ad/getAd";

    private void doBannerData() {
        new OkHttpUtils().get(bannerUrl).result(new HttpListener() {
            @Override
            public void success(String data) {
                BannerBean bannerBean = new Gson().fromJson(data, BannerBean.class);
                for (int i = 0; i < bannerBean.getData().size(); i++) {
                    String icon = bannerBean.getData().get(i).getIcon().replace("https", "http");
                    images.add(icon);
                    String title = bannerBean.getData().get(i).getTitle();
                    mTitle.add(title);
                }
                mBGABanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner bgaBanner, View view, Object o, int i) {
                        Glide.with(MainActivity.this).load(images.get(i)).into((ImageView) view);
                    }
                });
                mBGABanner.setData(images, mTitle);
            }
        });
    }
}
