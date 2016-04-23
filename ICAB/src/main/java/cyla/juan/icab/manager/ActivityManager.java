package cyla.juan.icab.manager;

import android.app.Activity;
import android.content.Context;

import java.util.Stack;

/**
 * Created by chen on 16-4-23.
 */
public class ActivityManager {
    private static Stack<Activity> activityStack;
    private static ActivityManager instance;

    /**
     * 单一实例
     * */
    public static ActivityManager getActivityManager() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 增加Activity到堆栈
     *
     *
     * */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity,最后一个压入堆栈的
     * */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity,最后一个压入堆栈的
     * */
    public void finishCurrentActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    private void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            if (!activity.isFinishing()) {
                activity.finish();
                activity = null;
            }
        }
    }

    /**
     * 结束指定类名的Activity
     * */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     * */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                Activity activity = activityStack.get(i);
                if (!activity.isFinishing()) {
                    activity.finish();
                    activity = null;
                }
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     * */
    public void AppExit(Context context) {
        try {
            finishAllActivity();
        }catch (Exception e) {
            //wait for log
        }
        // 杀死应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }

    /**
     * 移除指定Activity
     * */
    public void removeActivity(Activity activity) {
        if (activity != null ) {
            activityStack.remove(activity);
        }
    }

    /**
     * 移除指定Activity并置空
     * */
    public void removeActivityAndEmpty (Activity activity) {
        removeActivity(activity);
        activity = null;
    }
}
