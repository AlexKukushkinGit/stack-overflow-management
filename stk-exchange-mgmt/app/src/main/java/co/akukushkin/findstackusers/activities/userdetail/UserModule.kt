package co.akukushkin.findstackusers.activities.userdetail

import co.akukushkin.findstackusers.activities.userdetail.mvp.UserModel
import co.akukushkin.findstackusers.activities.userdetail.mvp.UserPresenter
import co.akukushkin.findstackusers.activities.userdetail.mvp.UserView
import co.akukushkin.findstackusers.app.api.model.StackUser
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides

@Module
class UserModule (private val activity: UserActivity, private val user: StackUser) {

    @Provides
    @UserScope
    fun view(): UserView {
        return UserView(activity)
    }

    @Provides
    @UserScope
    fun model(picasso: Picasso): UserModel {
        return UserModel(user, picasso)
    }


    @Provides
    @UserScope
    fun presenter(view: UserView, model: UserModel): UserPresenter {
        return UserPresenter(view, model)
    }

}