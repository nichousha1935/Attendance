package com.example.attendance.activity;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.example.attendance.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        LottieAnimationView lottieAnimationView = new LottieAnimationView(this);
//        lottieAnimationView.setAnimation("a_very_angry_sushi.json");
//        lottieAnimationView.playAnimation();
//        addContentView(lottieAnimationView,params);
//        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                lottieAnimationView.cancelAnimation();
//                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
//                finish();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.startInstant(SplashActivity.this);
                finish();
            }
        }, 3000);
    }
}
