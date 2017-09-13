package tk.danielgong.broadcastbestpractice

import android.app.Activity

/**
 * Created by gongzhq on 2017/9/13.
 */
class ActivityCollector {
    companion object {
        var activities: MutableList<Activity> = mutableListOf()
        fun addActivity(activity: Activity) {
            activities.add(activity)
        }

        fun removeActivity(activity: Activity) {
            activities.remove(activity)
        }

        fun finishAll() {
            for (activity in activities) {
                if (!activity.isFinishing) {
                    activity.finish()
                }
            }
        }
    }
}