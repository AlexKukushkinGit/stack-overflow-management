package co.akukushkin.findstackusers.activities.search.mvp

import co.akukushkin.findstackusers.activities.search.mvp.view.SearchView
import co.akukushkin.findstackusers.app.api.model.StackUser
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import co.akukushkin.findstackusers.util.RxImmediateSchedulerRule
import io.reactivex.subjects.PublishSubject
import org.junit.Assert


class SearchPresenterTest{

    @Rule @JvmField
    val rule = MockitoJUnit.rule()!!

    @Rule @JvmField var testSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var model:SearchModel

    @Mock
    lateinit var view: SearchView

    lateinit var presenter:SearchPresenter

    private val mockUsers:List<StackUser> = listOf()


    @Before
    @Throws(Exception::class)
    fun setUp() {
        presenter = SearchPresenter(view,model)
    }


    @Test
    fun onStartActivityWithSavedState(){

        val name = "name"
        //Given Saved State

        Mockito.`when`(model.getUsersFromSaveState()).thenReturn(Single.just(mockUsers))
        Mockito.`when`(model.getNameFromSavedState()).thenReturn(name)

        Mockito.`when`(view.getObservableClickDetailUser()).thenReturn(PublishSubject.create())
        Mockito.`when`(view.getUserNameTyped()).thenReturn("")

        //When Activity is Lunched
        presenter.onCreate()

        //Then the following methods should be called
        Mockito.verify<SearchView>(view, Mockito.times(1)).setUsersList(mockUsers)
        Mockito.verify<SearchView>(view, Mockito.times(1)).setSearchName(name)

        Assert.assertEquals(view.getUserNameTyped(),model.getNameFromSavedState())

        //At the end we just destroy
        presenter.onDestroy()
    }

    @Test
    fun onClick_GetUsers(){
        val name = "name"

        //Given Text Input
        Mockito.`when`(model.getUsersFromSaveState()).thenReturn(Single.never())
        Mockito.`when`(model.getListOfUsers(name)).thenReturn(Observable.just(mockUsers))

        Mockito.`when`(view.getObservableClickDetailUser()).thenReturn(PublishSubject.create())
        Mockito.`when`(view.getUserNameTyped()).thenReturn(name)

        //When User Click
        Mockito.`when`(view.getObservableClickSearchUser()).thenReturn(Observable.just(true))
        presenter.onCreate()

        //Then the following methods should be called
        Mockito.verify<SearchView>(view, Mockito.times(1)).setSearchButtonNotClickable()
        Mockito.verify<SearchView>(view, Mockito.times(1)).setSearchButtonClickable()
        Mockito.verify<SearchView>(view, Mockito.times(1)).setUsersList(mockUsers)

        //At the end we just destroy
        presenter.onDestroy()
    }

}