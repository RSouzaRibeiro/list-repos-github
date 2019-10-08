package com.rafaelsouza.listreposgithub.repository.service

import com.rafaelsouza.listreposgithub.repository.model.GitHubRepos
import com.rafaelsouza.listreposgithub.repository.model.GitHubReposSearch
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceRepos {

    @GET("repositories")
    fun listRepos() : Observable<ArrayList<GitHubRepos>>

    @GET("search/repositories")
    fun searchRepos(@Query("q") querySearch: String) : Observable<GitHubReposSearch>

    @GET("repositories/{id}")
    fun getRepoById(@Path("id") id: String) : Observable<GitHubRepos>
}