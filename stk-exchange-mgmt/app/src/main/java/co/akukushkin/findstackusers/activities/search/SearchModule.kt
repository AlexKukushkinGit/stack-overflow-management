package co.akukushkin.findstackusers.activities.search

import co.akukushkin.findstackusers.activities.search.mvp.SearchModel
import co.akukushkin.findstackusers.activities.search.mvp.SearchPresenter
import co.akukushkin.findstackusers.activities.search.mvp.view.SearchView
import co.akukushkin.findstackusers.app.api.client.StackClient
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val activity: SearchActivity) {

    @Provides
    @SearchScope
    fun view(): SearchView {
        return SearchView(activity)
    }

    @Provides
    @SearchScope
    fun model(stackClient: StackClient): SearchModel {
        return SearchModel(stackClient, activity)
    }


    @Provides
    @SearchScope
    fun presenter(view: SearchView, model: SearchModel): SearchPresenter {
        return SearchPresenter(view, model)
    }

}