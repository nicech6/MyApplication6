package com.ch.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TitleView titleView = findViewById(R.id.title);
        titleView.setOnTitleLeftClick(new TitleView.OnTitleLeftClick() {
            @Override
            public void leftTvClick() {

            }
        });
    }
}
