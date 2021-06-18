package com.ets.thcs.easythcsearch;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ets.thcs.easythcsearch.helper.Constants;
import com.ets.thcs.easythcsearch.splash.KenBurnsView;

public class SplashActivity extends Activity {

    private static int SPLASH_TIME_OUT = 5000;
    private KenBurnsView mKenBurns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mKenBurns = (KenBurnsView) findViewById(R.id.ken_burns_images);
        mKenBurns.setImageResource(R.drawable.splash_background);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (netConnected()) {
                    SharedPreferences sharedPreferences = getSharedPreferences(Constants.LOGIN_INFO,Context.MODE_PRIVATE);
                    boolean isLoggedIn = sharedPreferences.getBoolean(Constants.SESSION_ACTIVE,false);//|| isLoggedIn();
                    String loginAs=sharedPreferences.getString(Constants.LOGIN_AS,"");
                    String studentJson=sharedPreferences.getString("studentJson","");
                    System.out.println("isLoggedIn=> "+isLoggedIn);
                    System.out.println("loginAs=> "+loginAs);
                    System.out.println("studentJson=> "+studentJson);

                    if(isLoggedIn){
                        System.out.println("In IF");

                        Intent intent = new Intent(getApplicationContext(), TeacherOrStudentDetailsActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("TAG",loginAs);
                        intent.putExtra("studentJson",studentJson);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                    else {
                        System.out.println("In ELSE");
                        Intent intent = new Intent(getApplicationContext(), PromtLoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }


                }

                /*Intent intent = new Intent(getApplicationContext(), PromtLoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);*/
            }
        }, SPLASH_TIME_OUT);

    }

    private void setAnimation() {
//        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0F, 1.0F);
//        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//        scaleXAnimation.setDuration(1200);
//        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0F, 1.0F);
//        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//        scaleYAnimation.setDuration(1200);
//        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0F, 1.0F);
//        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
//        alphaAnimation.setDuration(1200);
//        AnimatorSet animatorSet = new AnimatorSet();
//        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
//        animatorSet.setStartDelay(500);
//        animatorSet.start();

        findViewById(R.id.imagelogo).setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_center);
        findViewById(R.id.imagelogo).startAnimation(anim);

    }
    private boolean netConnected() {
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
            System.out.println("Net connected = true");
        }
        return connected;
    }

    /*public boolean isLoggedIn() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null;
    }*/
}
