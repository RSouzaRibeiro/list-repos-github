package com.rafaelsouza.listreposgithub

import android.app.Application
import com.rafaelsouza.listreposgithub.module.ApiComponent
import com.rafaelsouza.listreposgithub.module.ApiModule
import com.rafaelsouza.listreposgithub.module.DaggerApiComponent

class BaseApplication: Application() {

    lateinit var graph: ApiComponent

    override fun onCreate() {
        super.onCreate()

        graph = DaggerApiComponent.builder().apiModule(ApiModule(this)).build()
    }
}