package com.xiong.hlbindicatordemo;

import android.graphics.Point;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ViewPager.OnPageChangeListener {

    int green;
    int halfWhite;
    int mWitch;

    private ViewPager viewPager;
    private TextView main_audio;
    private TextView main_video;
    private View main_mark;

    private MainAdapter adapter;
    private ArrayList<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        main_mark = findViewById(R.id.main_mark);
        main_video = (TextView) findViewById(R.id.main_video);
        main_audio = (TextView) findViewById(R.id.main_audio);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    protected void initData() {
        //初始化指示器宽度
        initMarkWidth();
        //初始化viewpager
        initViewPager();

        green = getResources().getColor(R.color.green);
        halfWhite = getResources().getColor(R.color.halfwhite);
    }

    private void initViewPager() {

        fragments.add(new VideoFragment());
        fragments.add(new AudioFragment());

        adapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        adapter.updateFragments(fragments);

    }

    private void initMarkWidth() {
        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        mWitch = point.x;
        main_mark.getLayoutParams().width=point.x/2;
        main_mark.requestLayout();
    }

    protected void initListener() {

        main_video.setOnClickListener(this);
        main_audio.setOnClickListener(this);

        viewPager.addOnPageChangeListener(this);
        //初始化title状态
        viewPager.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                handleTtitile(0);
                viewPager.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.main_video:
                viewPager.setCurrentItem(0);
                break;
            case R.id.main_audio:
                viewPager.setCurrentItem(1);
                break;
        }

    }

    /**
     * 指示器跟随ViewPager平移
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        float percent = positionOffsetPixels/(float)mWitch;
        int offsetX = (int) (percent*main_mark.getWidth());
//        position*指示器宽度+移动的距离
        int startX = position*main_mark.getWidth();
        int finalX = startX+offsetX;

        ViewCompat.setTranslationX(main_mark,finalX);
    }

    /**
     * 更新title的状态
     */
    @Override
    public void onPageSelected(int position) {
        handleTtitile(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //处理title的显示效果
    private void handleTtitile(int position) {
        //视频界面
        if(position==0){
            main_video.setTextColor(green);
            main_audio.setTextColor(halfWhite);

            ViewCompat.animate(main_video).scaleX(1.2f).scaleY(1.2f);
            ViewCompat.animate(main_audio).scaleX(1.0f).scaleY(1.0f);
        //音乐界面
        }else {
            main_video.setTextColor(halfWhite);
            main_audio.setTextColor(green);

            ViewCompat.animate(main_audio).scaleX(1.2f).scaleY(1.2f);
            ViewCompat.animate(main_video).scaleX(1.0f).scaleY(1.0f);
        }
    }

}
