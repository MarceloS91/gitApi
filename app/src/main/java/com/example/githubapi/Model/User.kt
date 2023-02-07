package com.example.githubapi.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User(
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("repos_url")
    var reposUrl: String,
    @SerializedName("name")
    var nome: String,
    @SerializedName("public_repo")
    var prublicRepo: Int

) : Serializable
