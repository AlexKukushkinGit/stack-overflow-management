package co.akukushkin.findstackusers.activities.userdetail

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import co.akukushkin.findstackusers.activities.userdetail.mvp.UserPresenter
import co.akukushkin.findstackusers.activities.userdetail.mvp.UserView
import co.akukushkin.findstackusers.app.FindStackUsersApp
import co.akukushkin.findstackusers.app.api.model.StackUser
import com.squareup.picasso.Picasso

import javax.inject.Inject

class UserActivity : AppCompatActivity() {

    @Inject
    lateinit var picasso: Picasso

    @Inject
    lateinit var UserView: UserView

    @Inject
    lateinit var UserPresenter: UserPresenter


    companion object {

        private const val EXTRA_USER:String = "EXTRA_USER"

        fun start(context: Context, user:StackUser){
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra(EXTRA_USER,user)
            context.startActivity(intent)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!intent.hasExtra(EXTRA_USER))
            throw IllegalArgumentException("Extra user is missing")

        val user = intent.getParcelableExtra<StackUser>(EXTRA_USER)

        FindStackUsersApp.createUserComponent(this,user).inject(this)

        setContentView(UserView)

        UserPresenter.onCreate()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        UserPresenter.onDestroy()
    }

}
