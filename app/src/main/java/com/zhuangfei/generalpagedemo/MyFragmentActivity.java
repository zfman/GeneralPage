package com.zhuangfei.generalpagedemo;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MyFragmentActivity extends AppCompatActivity implements View.OnClickListener{

    private TabFragment1 tabFragment1;
    private TabFragment2 tabFragment2;
    private TabFragment3 tabFragment3;
    private TabFragment4 tabFragment4;

    LinearLayout tabLayout1;
    LinearLayout tabLayout2;
    LinearLayout tabLayout3;
    LinearLayout tabLayout4;

    ImageView tabImageView1;
    ImageView tabImageView2;
    ImageView tabImageView3;
    ImageView tabImageView4;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        inits();
        select(0);
    }

    private void inits() {
        tabFragment1=new TabFragment1();
        tabFragment2=new TabFragment2();
        tabFragment3=new TabFragment3();
        tabFragment4=new TabFragment4();

        tabLayout1=findViewById(R.id.tabLayout1);
        tabLayout2=findViewById(R.id.tabLayout2);
        tabLayout3=findViewById(R.id.tabLayout3);
        tabLayout4=findViewById(R.id.tabLayout4);

        tabImageView1=findViewById(R.id.tabImageView1);
        tabImageView2=findViewById(R.id.tabImageView2);
        tabImageView3=findViewById(R.id.tabImageView3);
        tabImageView4=findViewById(R.id.tabImageView4);

        tabLayout1.setOnClickListener(this);
        tabLayout2.setOnClickListener(this);
        tabLayout3.setOnClickListener(this);
        tabLayout4.setOnClickListener(this);

    }

    private void initTab() {
        int color = getResources().getColor(R.color.app_gray);
        tabImageView1.setColorFilter(color);
        tabImageView2.setColorFilter(color);
        tabImageView3.setColorFilter(color);
        tabImageView4.setColorFilter(color);
    }

    private void select(int i) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        initTab();
        int color = getResources().getColor(R.color.theme_red);

        switch (i) {
            case 0:
                tabImageView1.setColorFilter(color);
                transaction.replace(R.id.frameLayout,tabFragment1);
                break;
            case 1:
                tabImageView2.setColorFilter(color);
                transaction.replace(R.id.frameLayout, tabFragment2);
                break;
            case 2:
                tabImageView3.setColorFilter(color);
                transaction.replace(R.id.frameLayout, tabFragment3);
                break;
            case 3:
                tabImageView4.setColorFilter(color);
                transaction.replace(R.id.frameLayout, tabFragment4);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tabLayout1:
                select(0);
                break;
            case R.id.tabLayout2:
                select(1);
                break;
            case R.id.tabLayout3:
                select(2);
                break;
            case R.id.tabLayout4:
                select(3);
                break;
            default:
                break;
        }
    }
}
