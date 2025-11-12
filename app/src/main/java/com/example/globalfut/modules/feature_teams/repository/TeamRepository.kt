package com.example.globalfut.modules.feature_teams.repository

import com.example.globalfut.modules.feature_teams.data.Team
import com.example.globalfut.modules.feature_teams.data.remote.TeamRemote
import com.example.globalfut.modules.feature_teams.data.remote.TeamService

class TeamRepository(private val service: TeamService) {

    suspend fun getTeams(): List<Team> {
        val remoteTeams: List<TeamRemote> = service.getTeams()
        return remoteTeams.map {
            Team(
                id = it.id,
                name = it.name,
                city = it.city,
                logoUrl = it.logoUrl,
                postText = it.postText
            )
        }
    }
}
