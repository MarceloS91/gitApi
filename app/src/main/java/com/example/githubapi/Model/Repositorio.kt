package com.example.githubapi.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Repositorio(
    @SerializedName("name")
    var nome: String,
    @SerializedName("language")
    var linguagem: String,
    @SerializedName("html_url")
    var htmlUrl: String
) : Serializable
