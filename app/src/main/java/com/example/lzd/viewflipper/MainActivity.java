package com.example.lzd.viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;
/*
    2016/10/22 made by LZD
    使用ViewFlipper实现图片轮播效果
    并添加手势控制图片轮播
 */
public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;
    private int[]resId = {R.drawable.background,R.drawable.background2,R.drawable.background3,R.drawable.background4};
    private float startX;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);
        //动态导入
        for(int i = 0;i < resId.length;i++) {
            viewFlipper.addView(getImagieView(resId[i]));
        }
        //设置动画效果
        viewFlipper.setInAnimation(this,R.anim.right_in);
        viewFlipper.setInAnimation(this,R.anim.right_out);
        //设置动画轮播时间
        viewFlipper.setFlipInterval(3000);
        //开启轮播
        viewFlipper.startFlipping();
    }

    //监控手势
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指落下
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                break;
            //手指移动
            case MotionEvent.ACTION_MOVE:
                if(event.getX()-startX>100) {
                    //to left
                    viewFlipper.setInAnimation(this,R.anim.left_in);
                    viewFlipper.setInAnimation(this,R.anim.left_out);
                    //显示上一页
                    viewFlipper.showPrevious();
                }else if(startX-event.getX()>100) {
                    //to right
                    viewFlipper.setInAnimation(this,R.anim.right_in);
                    viewFlipper.setInAnimation(this,R.anim.right_out);
                    //显示下一页
                    viewFlipper.showNext();
                }
                break;
            //手指离开屏幕
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(event);
    }

    public ImageView getImagieView(int resId) {
        ImageView imageView = new ImageView(this);
        //imageView.setImageResource(resId);
        //为显示图片占满整个屏幕
        imageView.setBackgroundResource(resId);
        return imageView;
    }
}
