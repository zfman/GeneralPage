package com.zhuangfei.generalpagedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button activityButton;
    Button fragmentButton;
    Button customButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inits();
    }

    private void inits() {
        activityButton=findViewById(R.id.bt_activity);
        fragmentButton=findViewById(R.id.bt_fragment);
        customButton=findViewById(R.id.bt_customview);

        activityButton.setOnClickListener(this);
        fragmentButton.setOnClickListener(this);
        customButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_activity:
                Intent intent=new Intent(MainActivity.this,ExampleActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_fragment:
                Intent intent2=new Intent(MainActivity.this,MyFragmentActivity.class);
                startActivity(intent2);
                break;
            case R.id.bt_customview:
                Intent intent3=new Intent(MainActivity.this,CustomViewActivity.class);
                startActivity(intent3);
                break;
        }
    }
}
