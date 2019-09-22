package co.akukushkin.findstackusers.activities.userdetail

import dagger.Subcomponent


@UserScope
@Subcomponent(modules = [(UserModule::class)])
interface UserComponent {

    fun inject(searchActivity: UserActivity)

}