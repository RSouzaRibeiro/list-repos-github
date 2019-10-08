package com.rafaelsouza.listreposgithub.viewmodel


import android.util.Log
import androidx.lifecycle.ViewModel
import com.rafaelsouza.listreposgithub.repository.service.ServiceRepos

import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BaseViewModel: ViewModel() {

    var disposables = CompositeDisposable()

    @Inject
    lateinit var service: ServiceRepos

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
        Log.d("ViewModel", "onCleared")
    }
}