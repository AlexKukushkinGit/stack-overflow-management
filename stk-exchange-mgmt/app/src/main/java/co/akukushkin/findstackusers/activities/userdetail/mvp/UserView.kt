package co.akukushkin.findstackusers.activities.userdetail.mvp

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.widget.FrameLayout
import co.akukushkin.findstackusers.R
import co.akukushkin.findstackusers.activities.userdetail.UserActivity
import co.akukushkin.findstackusers.app.api.model.StackUser
import co.akukushkin.findstackusers.util.DateFormatUtils
import kotlinx.android.synthetic.main.activity_user_detail.view.*

@SuppressLint("ViewConstructor")
class UserView(private val activity: UserActivity):FrameLayout(activity) {

    init {
        inflate(activity,R.layout.activity_user_detail,this)
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        activity.supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    fun setUser(stackUser: StackUser){

        activity.supportActionBar?.title = stackUser.displayName

        userName.text = stackUser.displayName
        userReputation.text = ""+stackUser.reputation
        tvUserBadges.text = stackUser.badgeCounts.toString()
        tvUserLocation.text = stackUser.location
        
        tvUserAge.text = if (stackUser.age == 0) 
            context.getString(R.string.age_no_available) 
        else ""+stackUser.age
        
        tvUserCreation.text = DateFormatUtils.parseDate(stackUser.creationDate)

    }

    fun setImage(userImage:Bitmap){
        ivUserImage.setImageBitmap(userImage)
    }

}