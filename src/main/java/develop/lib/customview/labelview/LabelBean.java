package develop.lib.customview.labelview;

import java.io.Serializable;

/**
 * Created by xiaohe on 17-9-8.
 * 扩展可自定义背景样式和文字颜色用于多背景多文字颜色标签需求
 */

public class LabelBean implements Serializable{
    private int backGroundId;
    private int textColorId;
    private String content;

    public int getBackGroundId() {
        return backGroundId;
    }

    public void setBackGroundId(int backGroundId) {
        this.backGroundId = backGroundId;
    }

    public int getTextColorId() {
        return textColorId;
    }

    public void setTextColorId(int textColorId) {
        this.textColorId = textColorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
