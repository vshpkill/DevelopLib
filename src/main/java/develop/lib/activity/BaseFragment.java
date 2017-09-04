package develop.lib.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by KKK on 2017/9/4.
 */

public abstract class BaseFragment extends Fragment {
    protected Activity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(activity).inflate(getLayoutId(), container, false);
        initViews(view, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initVariables();
        loadData();
    }

    protected abstract void loadData();

    protected abstract void initVariables();

    protected abstract void initViews(View view, Bundle savedInstanceState);

    protected abstract int getLayoutId();
}
