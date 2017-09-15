package develop.lib.animation.tweenanim;

import android.content.Context;
import android.view.ViewPropertyAnimator;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

/**
 * Created by xiaohe on 17-9-14.
 */

public class TweenAnimBuilder {
    /**
     * 以动画的定时改变视图的透明度
     *
     * @param fromAlpha
     * @param toAlpha
     * @return
     */
    public static AlphaAnimation getAlphAnim(float fromAlpha, float toAlpha) {
        return new AlphaAnimation(fromAlpha, toAlpha);
    }

    public static RotateAnimation getRotateAnim(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
        RotateAnimation animation = new RotateAnimation(fromDegrees, toDegrees, pivotX, pivotY);
        return animation;
    }

    public static ScaleAnimation getScaleAnim(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY) {
        return new ScaleAnimation(fromX, toX, fromY, toY, pivotX, pivotY);
    }

    public static TranslateAnimation getTranslateAnim(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta) {
        return new TranslateAnimation(fromXDelta, toXDelta, fromYDelta, toYDelta);
    }

    public static AnimationSet getAnimationSet(){
        AnimationSet animationSet = new AnimationSet(true);
        return animationSet;
    }
}
