package com.okhttp.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.javaandroid.main.R;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OKhttpActivity extends AppCompatActivity {


    @Bind(R.id.tv_show)
    TextView tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);

        httpInternet();
    }

    private void httpInternet() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("uid", "23434");
        map.put("version", "1.0");
        map.put("plat", "a");
        //String BaseUrl=
        //String BaseUrl="http://ad.wodpy.com:81/meplusd/hotVideoHome.html"
        //String BaseUrl="http://ad.wodpy.com:81/meplusd/attentionVideoHome.html?uid=23434&version=1.0&plat=a"
        RequestManager.getInstance(this, "http://ad.wodpy.com:81/meplusd")
                .requestAsyn(UserListBean.class,
                        "/attentionVideoHome.html",
                        0,
                        map,
                        new ReqCallBack<UserListBean>() {
                    @Override
                    public void onReqSuccess(UserListBean result) {
                        System.out.println(result.toString());
                        tvShow.setText(result.toString());
                    }

                    @Override
                    public void onReqFailed(String errorMsg) {
                        System.out.println(errorMsg);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);

    }

}

