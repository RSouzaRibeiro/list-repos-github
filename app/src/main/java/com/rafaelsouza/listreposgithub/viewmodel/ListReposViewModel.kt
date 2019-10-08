package com.rafaelsouza.listreposgithub.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rafaelsouza.listreposgithub.extension.androidSubscribe
import com.rafaelsouza.listreposgithub.repository.model.GitHubRepos
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ListReposViewModel : BaseViewModel {

    @Inject
    constructor()

    constructor(p0: Any)

    var repos = MutableLiveData<ArrayList<GitHubRepos>>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()


    fun listRepos() {
        disposables.add(service.listRepos()
            .androidSubscribe()
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe({
                if (it.size > 0) {
                    repos.postValue(it)
                }

            },
                {
                    error.postValue(it.localizedMessage)
                })
        )
    }

    fun listSearchRepos(querySearch: String) {
        disposables.add(service.searchRepos(querySearch)
            .debounce(500, TimeUnit.MILLISECONDS)
            .androidSubscribe()
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe(
                {
                    if (it.repos!!.size > 0) {
                        repos.postValue(it.repos)
                    }
                },
                {
                    error.postValue(it.localizedMessage)
                }
            ))
    }
}