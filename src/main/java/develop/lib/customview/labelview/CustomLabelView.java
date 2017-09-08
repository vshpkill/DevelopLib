package develop.lib.customview.labelview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import develop.lib.R;

/**
 * Created by dayima on 17-8-2.
 * 自定义标签View
 * 自动适配屏幕宽度，实现错落效果
 */

public class CustomLabelView extends ViewGroup {
    public CustomLabelView(Context context) {
        this(context, null);
    }

    public CustomLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private boolean isSingleLine;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int withMeasureSize = MeasureSpec.getSize(widthMeasureSpec);
        int withMeasureMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightMeasureSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);

        int groupWith = 0;
        int groupHeight = 0;

        int lineWith = 0;
        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();
        int pm = getPaddingBottom();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWith = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            if (i == 0) {
                groupHeight += childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
            }
            lineWith += (childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
            if ((lineWith + pl + pr) > withMeasureSize) {
                if (isSingleLine) {
                    break;
                }
                groupHeight += childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;
                groupWith = Math.max(groupWith, lineWith);
                lineWith = childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
            }
        }
        withMeasureSize = withMeasureMode == MeasureSpec.EXACTLY ? withMeasureSize : (groupWith + pl + pr);
        heightMeasureSize = heightMeasureMode == MeasureSpec.EXACTLY ? heightMeasureSize : (groupHeight + pt + pm);
        setMeasuredDimension(withMeasureSize, heightMeasureSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childl = 0;
        int childt = 0;
        int childr = 0;
        int childb = 0;

        int with = getMeasuredWidth();

        int linNum = 1;

        int pl = getPaddingLeft();
        int pt = getPaddingTop();
        int pr = getPaddingRight();

        int lineWith = 0;

        int childCount = getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) child.getLayoutParams();
            int childWith = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            lineWith += (childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);

            if ((lineWith + pl + pr) > with) {
                if (isSingleLine) {
                    break;
                }
                linNum++;
                childl = marginLayoutParams.leftMargin + pl;
                lineWith = childWith + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                childr = lineWith + pl - marginLayoutParams.rightMargin;
            } else {
                if (i == 0) {
                    childl = marginLayoutParams.leftMargin + pl;
                } else {
                    childl = lineWith + pl - childWith - marginLayoutParams.leftMargin;
                }
                childr = lineWith + pl - marginLayoutParams.rightMargin;
            }


            childt = linNum * (childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin) - childHeight - marginLayoutParams.bottomMargin + pt;
            childb = linNum * (childHeight + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin) - marginLayoutParams.bottomMargin + pt;
            child.layout(childl, childt, childr, childb);
        }
    }

    /**
     * @param labelArray
     * @param isSingleLine 是否只显示一行
     * @param bgHasCorner  标签背景是否有圆角
     */
    public void setLabelData(List<String> labelArray, boolean isSingleLine, boolean bgHasCorner) {
        this.isSingleLine = isSingleLine;
        removeAllViews();
        for (int i = 0; i < labelArray.size(); i++) {
            String label = labelArray.get(i);
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.textview_labelview, CustomLabelView.this, false);
            if (bgHasCorner) {
                tv.setBackgroundResource(R.drawable.bg_labelview_corner);
            } else {
                tv.setBackgroundResource(R.drawable.bg_labelview_nocorner);
            }
            tv.setText(label);
            addView(tv);
        }
    }

    /**
     * @param labelArray
     * @param layoutId     TextView布局id 仿照R.layout.textview_labelview
     * @param isSingleLine 是否只显示一行
     */
    public void setLabelData(List<String> labelArray, int layoutId, boolean isSingleLine) {
        this.isSingleLine = isSingleLine;
        removeAllViews();
        for (int i = 0; i < labelArray.size(); i++) {
            String label = labelArray.get(i);
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(layoutId, CustomLabelView.this, false);
            tv.setText(label);
            addView(tv);
        }
    }

    /**
     * @param labelArray 可定义不同背景和文字颜色
     * @param layoutId  TextView布局id 仿照R.layout.textview_labelview
     * @param isSingleLine 是否只显示一行
     */
    public void setLabel(List<LabelBean> labelArray, int layoutId, boolean isSingleLine) {
        this.isSingleLine = isSingleLine;
        removeAllViews();
        for (int i = 0; i < labelArray.size(); i++) {
            LabelBean label = labelArray.get(i);
            TextView tv = (TextView) LayoutInflater.from(getContext()).inflate(layoutId, CustomLabelView.this, false);
            tv.setText(label.getContent());
            tv.setTextColor(label.getTextColorId());
            tv.setBackgroundColor(label.getBackGroundId());
            addView(tv);
        }
    }

    /**
     * 与当前ViewGroup对应的LayoutParams
     */
    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }
}
