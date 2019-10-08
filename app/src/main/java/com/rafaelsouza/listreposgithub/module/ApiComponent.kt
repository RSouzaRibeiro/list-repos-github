package com.rafaelsouza.listreposgithub.module
import com.rafaelsouza.listreposgithub.view.DetailsRepoActivity
import com.rafaelsouza.listreposgithub.view.ListReposActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(activity: ListReposActivity)
    fun inject(activity: DetailsRepoActivity)

}