package com.rafaelsouza.listreposgithub.repository.model

import com.google.gson.annotations.SerializedName

class GitHubRepos {


    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("description")
    var description: String = ""

    @SerializedName("owner")
    var owner: GitHubOwner? = null
}