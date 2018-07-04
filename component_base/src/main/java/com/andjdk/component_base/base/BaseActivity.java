package com.andjdk.component_base.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by andjdk on 2018/7/4.
 * Description:
 */
public abstract class BaseActivity extends AppCompatActivity {


    public BaseActivity mActivity ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        //加入activity管理
        BaseApplication.getAppContext().getActivityControl().addActivity(this);
        mActivity = this ;

        initView();


    }
    /**
     * 获取当前Activity的UI布局
     *
     * @return 布局id
     */
    protected abstract int getLayoutId();

    /**
     * 初始化数据
     */
    protected abstract void initView();

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除类
        BaseApplication.getAppContext().getActivityControl().removeActivity(this);

    }


}
