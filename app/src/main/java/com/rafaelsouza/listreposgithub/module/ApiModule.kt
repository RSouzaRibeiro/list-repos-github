package com.rafaelsouza.listreposgithub.module


import android.content.Context
import com.rafaelsouza.listreposgithub.BuildConfig
import com.rafaelsouza.listreposgithub.repository.service.ServiceRepos
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule(private val context: Context) {




    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val ambiente = BuildConfig.SERVER_URL

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(ambiente)
            .build()
    }

    @Provides
    fun getClient(): ServiceRepos = getRetrofit().create(ServiceRepos::class.java)

    /*@Provides
    @Singleton
    fun getDatabase(): LocalDatabase =
        Room.databaseBuilder(context.applicationContext, LocalDatabase::class.java, "local_storage").build()*/



}