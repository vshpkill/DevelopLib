package develop.lib.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by KKK on 2017/9/4.
 */

public abstract class BaseActivity extends Activity {
    protected Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        initViews();
        initVariables();
        loadData();
    }

    protected abstract void initViews();

    protected abstract void initVariables();

    protected abstract void loadData();
}
