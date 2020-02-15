package com.android.discovery.viewtest.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.discovery.viewtest.Adapter.FragmentAdapter;
import com.android.discovery.viewtest.Fragment.MainFragment;
import com.android.discovery.viewtest.Fragment.MineFragment;
import com.android.discovery.viewtest.Listener.RetrofitListener;
import com.android.discovery.viewtest.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, RetrofitListener {

    @BindView(R.id.main_viewPager)
    ViewPager mainViewPager;
    @BindView(R.id.main_main)
    TextView mainMain;
    @BindView(R.id.main_mine)
    TextView mainMine;
    @BindView(R.id.main_search)
    ImageView mainSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
        ButterKnife.bind(this);
    }

    private void initViewPager() {

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new MainFragment());
        fragments.add(new MineFragment());

        mainViewPager = findViewById(R.id.main_viewPager);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);
        mainViewPager.
                setAdapter(fragmentAdapter);
        mainViewPager.setOffscreenPageLimit(5);
        mainViewPager.setOnPageChangeListener(this);

    }

    @OnClick({R.id.main_main, R.id.main_mine, R.id.main_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_main:
                mainViewPager.setCurrentItem(0);
                break;
            case R.id.main_mine:
                mainViewPager.setCurrentItem(1);
                break;
            case R.id.main_search:
                Intent intent = new Intent(this,SearchActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mainViewPager.setCurrentItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onFailed(String errorString) {

    }

    @Override
    public void onSuccess(Object o, int flag) {

    }

    @Override
    public void onFinish() {

    }
}
