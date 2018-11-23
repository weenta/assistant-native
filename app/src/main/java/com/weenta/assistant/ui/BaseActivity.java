package com.weenta.assistant.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    /**
     * toast
     * @param msg
     */
    protected void toast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
