package develop.lib.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;

/**
 * Created by KKK on 2017/9/4.
 */

public abstract class BaseActivity extends Activity {
    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setAnimation();
            openImmerseStatasBarMode(this);
        }
        context = this;
        initViews();
        initVariables();
        loadData();
    }

    public static void openImmerseStatasBarMode(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        }
    }

    /**
     * 设置转场效果，如应用此方法请务必调用BaseActivity提供的startActivity方法
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setAnimation() {
        switch (AppConfig.ACTIVITYANIMATION) {
            //分解
            case 1:
                getWindow().setEnterTransition(new Explode().setDuration(2000));
                getWindow().setExitTransition(new Explode().setDuration(2000));
                break;
            //滑入
            case 2:
                getWindow().setEnterTransition(new Slide().setDuration(2000));
                getWindow().setExitTransition(new Slide().setDuration(2000));
                break;
            //淡入淡出
            case 3:
                getWindow().setEnterTransition(new Fade().setDuration(2000));
                getWindow().setExitTransition(new Fade().setDuration(2000));
                break;
        }
    }

    protected abstract void initViews();

    protected abstract void initVariables();

    protected abstract void loadData();

    /**
     * 适配5.0以上加入转场动画效果
     *
     * @param intent
     */
    public void startActivity(Intent intent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(BaseActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    /**
     * 适配5.0以上加入转场动画效果
     *
     * @param aClass
     */
    public void startActivity(Class aClass) {
        Intent intent = new Intent(context, aClass);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(BaseActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }
}
