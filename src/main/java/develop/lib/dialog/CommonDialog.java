package develop.lib.dialog;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by xiaohe on 17-9-13.
 * 弹框类用于弹出对话框操作
 */

public class CommonDialog {

    public static void showCommonDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("一个弹框");
        builder.show();
    }

}
