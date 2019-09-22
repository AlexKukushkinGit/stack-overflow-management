package co.akukushkin.findstackusers.app

import android.app.Application
import android.content.Context
import co.akukushkin.findstackusers.BuildConfig
import co.akukushkin.findstackusers.activities.search.SearchActivity
import co.akukushkin.findstackusers.activities.search.SearchComponent
import co.akukushkin.findstackusers.activities.search.SearchModule
import co.akukushkin.findstackusers.activities.userdetail.UserActivity
import co.akukushkin.findstackusers.activities.userdetail.UserComponent
import co.akukushkin.findstackusers.activities.userdetail.UserModule
import co.akukushkin.findstackusers.app.api.APIModule
import co.akukushkin.findstackusers.app.api.model.StackUser


class FindStackUsersApp: Application() {

    private lateinit var navigator:Navigator
    private lateinit var context:Context

    //SubComponents
    companion object {
        private lateinit var appComponent:AppComponent

        @JvmStatic
        fun createMainComponent(searchActivity: SearchActivity): SearchComponent =
                appComponent.plusSearchComponent(SearchModule(searchActivity))

         @JvmStatic
        fun createUserComponent(activity: UserActivity, user: StackUser): UserComponent =
                appComponent.plusUserComponent(UserModule(activity, user))


    }

    override fun onCreate() {
        super.onCreate()

        navigator = Navigator()
        context = applicationContext

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(navigator))
                .aPIModule(APIModule(BuildConfig.SERVER_URL))
                .picassoModule(PicassoModule(context))
                .build()


    }


}

