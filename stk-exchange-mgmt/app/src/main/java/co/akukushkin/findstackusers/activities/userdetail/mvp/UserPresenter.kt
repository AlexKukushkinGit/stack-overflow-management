package co.akukushkin.findstackusers.activities.userdetail.mvp

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers


class UserPresenter(val view: UserView, val model: UserModel) {

    private val disposables = CompositeDisposable()

    fun onCreate(){

        subscribe(observeImageFromUrl())
        view.setUser(model.user)

    }

    fun onDestroy(){
        disposables.clear()
    }

    private fun observeImageFromUrl():Disposable{
        return model.getUserAvatar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            view.setImage(it)
                        },
                        {
                            Log.d("NO IMAGE","COULDN'T DOWNLOAD IMAGE")
                        }
                )
    }


    private fun subscribe(disposable: Disposable){
        disposables.add(disposable)
    }
}