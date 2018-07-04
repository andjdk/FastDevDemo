package com.andjdk.component_base.controller;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by andjdk on 2018/7/4.
 * Description:管理所有Activity
 */
public class ActivityControl {
    private Set<Activity> allActivities = new HashSet<>();
    private WeakReference<Activity> currentAtivity;

    /**
     * 设置当前运行的Activity
     * @param currentAtivity
     */
    public void setCurrentAtivity(Activity currentAtivity) {
        this.currentAtivity = new WeakReference<>(currentAtivity);
    }

    /**
     * 获取当前运行的Activity,有可能返回null
     * @return
     */
    public Activity getCurrentAtivity() {
        return currentAtivity.get();
    }

    /**
     *  添加Activity到管理
     * @param act
     */
    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    /**
     * 从管理器移除Activity，一般在Ondestroy移除，防止强引用内存泄漏
     * @param act
     */
    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }

    /**
     * 关闭所有Activity
     */
    public void finishiAll() {
        if (allActivities != null) {
            for (Activity act : allActivities) {
                act.finish();
            }
        }
    }

    /**
     * 关闭所有Activity 除了对应的activity
     * @param activity
     */
    public void finishiAllExcept(Activity activity) {
        if (allActivities != null) {
            for (Activity act : allActivities) {
                if (act!=activity){
                    act.finish();
                }
            }
        }
    }

    /**
     * 退出应用程序
     */
    public void appExit() {
        try {
            finishiAll();
            // 杀死该应用进程
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
