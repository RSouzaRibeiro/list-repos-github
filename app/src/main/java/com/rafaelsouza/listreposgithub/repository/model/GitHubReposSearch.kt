package com.rafaelsouza.listreposgithub.repository.model

import com.google.gson.annotations.SerializedName

class GitHubReposSearch {

    @SerializedName("items")
    var repos: ArrayList<GitHubRepos>? = null
}