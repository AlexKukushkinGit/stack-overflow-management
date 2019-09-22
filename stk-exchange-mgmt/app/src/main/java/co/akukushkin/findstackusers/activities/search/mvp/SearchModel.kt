package co.akukushkin.findstackusers.activities.search.mvp

import android.os.Bundle
import co.akukushkin.findstackusers.activities.search.SearchActivity
import co.akukushkin.findstackusers.app.api.client.StackClient
import co.akukushkin.findstackusers.app.api.model.StackUser
import co.akukushkin.findstackusers.util.savestate.BundleAction
import co.akukushkin.findstackusers.util.savestate.ReactiveSaveState
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.ArrayList


open class SearchModel(private val stackClient: StackClient, private val activity: SearchActivity) {

    companion object {
        const val BUNDLE_LIST = "BUNDLE_LIST"
        const val BUNDLE_NAME = "BUNDLE_NAME"
    }


    private var currentName:String = ""
    private var users : List<StackUser> = ArrayList<StackUser>()
    private val reactiveSaveState = ReactiveSaveState()


    /**
     *  Get users calling API
     *
     *  @param name user name
     *  @return List of stack users
     */
    open fun getListOfUsers(name:String):Observable<List<StackUser>>{

        if (currentName != name){

            return stackClient.getStackUsers(name)
                    .subscribeOn(Schedulers.io())
                    .map {
                        pagedResponse ->
                            users = pagedResponse.items
                            currentName = name

                            saveEventsState(users)

                        users
                    }

        }
       return Observable.empty()
    }


    open fun saveEventsState(list: List<StackUser>) {

        reactiveSaveState.updateSaveState(activity, object : BundleAction {
            override fun call(bundle: Bundle) {

                bundle.putParcelableArrayList(BUNDLE_LIST, list as ArrayList )
                bundle.putString(BUNDLE_NAME, currentName)

            }
        })

    }


    open fun getUsersFromSaveState(): Single<List<StackUser>> {
        return areUsersInSaveState()
                .isEmpty.flatMap {
            _ ->
            Single.just(users)
        }
    }


    open fun getNameFromSavedState():String{
        return currentName
    }


    private fun areUsersInSaveState(): Maybe<List<StackUser>> {
        return reactiveSaveState.getSavedState(activity).map { bundle ->
            currentName = bundle.getString(BUNDLE_NAME)
            users = bundle.getParcelableArrayList(BUNDLE_LIST)
            users
        }
    }
}