package co.akukushkin.findstackusers.app

import co.akukushkin.findstackusers.activities.search.SearchComponent
import co.akukushkin.findstackusers.activities.search.SearchModule
import co.akukushkin.findstackusers.activities.userdetail.UserComponent
import co.akukushkin.findstackusers.activities.userdetail.UserModule
import co.akukushkin.findstackusers.app.api.APIModule
import co.akukushkin.findstackusers.app.api.client.StackClient
import com.squareup.picasso.Picasso
import dagger.Component


@AppScope
@Component(modules = [(AppModule::class), (APIModule::class), (PicassoModule::class)])
interface AppComponent {

    fun stackClient(): StackClient

    fun navigator(): Navigator

    fun picasso():Picasso

    //SubComponents
    fun plusSearchComponent(searchModule: SearchModule): SearchComponent
    fun plusUserComponent(UserModule: UserModule): UserComponent

}