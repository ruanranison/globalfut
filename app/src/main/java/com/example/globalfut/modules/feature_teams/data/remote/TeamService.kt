package com.example.globalfut.modules.feature_teams.data.remote

import retrofit2.http.GET

data class TeamRemote(
    val id: Int,
    val name: String,
    val city: String,
    val logoUrl: String,
    val postText: String
)

interface TeamService {
    @GET("teams")
    suspend fun getTeams(): List<TeamRemote>
}
