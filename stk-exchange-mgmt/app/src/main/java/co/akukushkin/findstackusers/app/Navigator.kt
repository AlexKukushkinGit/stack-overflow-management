package co.akukushkin.findstackusers.app

import android.app.Activity
import co.akukushkin.findstackusers.activities.userdetail.UserActivity
import co.akukushkin.findstackusers.app.api.model.StackUser


class Navigator {

    fun navigateToUserDetail(context: Activity, user:StackUser) {
        UserActivity.start(context,user)
    }
}