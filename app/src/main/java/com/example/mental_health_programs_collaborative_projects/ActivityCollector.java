package com.example.mental_health_programs_collaborative_projects;

import android.app.Activity;
import java.util.ArrayList;

/**
 * 管理Activity集合的工具类。
 * 用于跟踪应用程序中所有打开的Activity，并提供方法来统一管理这些Activity的生命周期。
 */
public class ActivityCollector {
    // 存储所有Activity实例的列表
    public static ArrayList<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }


    public static void finishAll() {
        for (Activity activity : activities) {
            // 检查Activity是否正在结束中，如果不是，则调用finish方法结束它
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        // 结束所有Activity后，清空列表
        activities.clear();
    }
}