package com.example.recyclerviewscroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_non_paging).setOnClickListener(this);
        findViewById(R.id.main_paging).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_paging:
                startActivity(new Intent(this, PagingView.class));
                break;

            case R.id.main_non_paging:
                startActivity(new Intent(this, NonPaging.class));
                break;
        }
    }
}
