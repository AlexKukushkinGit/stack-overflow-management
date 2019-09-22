package co.akukushkin.findstackusers.app.api

import co.akukushkin.findstackusers.app.AppScope
import co.akukushkin.findstackusers.app.api.client.StackClient
import dagger.Module
import dagger.Provides


@Module
class APIModule(private val baseURL:String) {

    @AppScope
    @Provides
    fun provideStackClient(): StackClient {
        return StackClient(baseURL)
    }
}