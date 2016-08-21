package cyla.juan.icab.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import cyla.juan.icab.R;
import cyla.juan.icab.ui.UIHelper;

/**
 * app的欢迎界面
 */
public class WelcomePage extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);


        ImageView show = (ImageView) findViewById(R.id.show_iv_welcome_falsh);
        //渐变展示启动屏

        show.setImageResource(R.mipmap.bg_logo);
        AlphaAnimation aa = new AlphaAnimation(0.3f, 1.0f);
        aa.setDuration(2000);
        show.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                UIHelper.goMainActivity(WelcomePage.this);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }
}
