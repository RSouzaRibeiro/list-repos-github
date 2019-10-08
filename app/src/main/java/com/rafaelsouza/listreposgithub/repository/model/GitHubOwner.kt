package com.rafaelsouza.listreposgithub.repository.model

import com.google.gson.annotations.SerializedName

class GitHubOwner {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("login")
    var login: String = ""

    @SerializedName("avatar_url")
    var avatar_url: String = ""


}