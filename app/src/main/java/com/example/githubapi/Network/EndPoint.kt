package com.example.githubapi.Network

import com.example.githubapi.Model.Repositorio
import com.example.githubapi.Model.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EndPoint {
    @GET("/users/{userName}")
    fun getUsers(
        @Path("userName")
        userName: String
    ) : Call<User>

    @GET("/users/{userName}/repos")
    fun getRepos(
        @Path("userName")
        userName: String
    ) : Call<List<Repositorio>>
}
