package com.rafaelsouza.listreposgithub.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rafaelsouza.listreposgithub.extension.androidSubscribe
import com.rafaelsouza.listreposgithub.repository.model.GitHubRepos
import javax.inject.Inject

class DetailsRepoViewModel : BaseViewModel {

    @Inject
    constructor()

    var repo = MutableLiveData<GitHubRepos>()
    var progress = MutableLiveData<Boolean>()
    var error = MutableLiveData<String>()

    fun getRepoById(idRepo: String) {
        disposables.add(service.getRepoById(idRepo)
            .androidSubscribe()
            .doOnSubscribe { progress.value = true }
            .doFinally { progress.value = false }
            .subscribe({repository ->
                if(repository.id != null){
                    repo.postValue(repository)
                }

            },
                {
                    error.postValue(it.localizedMessage)
                })
        )
    }

}