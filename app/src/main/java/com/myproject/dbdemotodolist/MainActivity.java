package com.myproject.dbdemotodolist;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.myproject.dbdemotodolist.View.MainActivityViewImplementor;

public class MainActivity extends AppCompatActivity {

    MainActivityViewImplementor mvcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mvcView = new MainActivityViewImplementor(MainActivity.this, null);
        setContentView(mvcView.getRootView());
        mvcView.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mvcView.bindDataToView();
    }
}