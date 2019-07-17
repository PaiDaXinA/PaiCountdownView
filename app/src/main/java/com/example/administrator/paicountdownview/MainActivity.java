package com.example.administrator.paicountdownview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.paicountdownviews.PaiCountdownView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private PaiCountdownView paiCountdownView;
    private int count = 0;

    Handler mHandler = new Handler();
    Runnable r = new Runnable() {

        @Override
        public void run() {
            //do something
            //每隔1s循环执行run方法
            count = count + 10;
            paiCountdownView.setSum(count);
            mHandler.postDelayed(this, 1000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        paiCountdownView.setY(paiCountdownView.getTop());
        paiCountdownView.setFinishColor(com.example.paicountdownviews.R.color.blue_item);
        paiCountdownView.setInitialColor(com.example.paicountdownviews.R.color.red);

        mHandler.postDelayed(r, 100);//延时100毫秒
    }

    private void initView() {
        paiCountdownView = findViewById(R.id.paiCountdownView);
    }
}
