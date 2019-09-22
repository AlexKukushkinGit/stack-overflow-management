package co.akukushkin.findstackusers.activities.userdetail.mvp

import android.graphics.Bitmap
import co.akukushkin.findstackusers.app.api.model.StackUser
import com.squareup.picasso.Picasso
import io.reactivex.Single


class UserModel(val user: StackUser,  val picasso: Picasso) {


    fun getUserAvatar(): Single<Bitmap> = Single.create {
        try {
            if (!it.isDisposed) {
                val avatar: Bitmap = picasso.load(user.profileAvatar).get()
                it.onSuccess(avatar)
            }
        } catch (e: Throwable) {
            if (!it.isDisposed)
                it.onError(e)
        }
    }


}