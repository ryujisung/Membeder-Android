package com.heechan.membeder.model.remote

import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.CreateTeamReq
import com.heechan.membeder.model.data.team.Team
import retrofit2.Response

interface TeamRepository {
    suspend fun createTeam(teamData : CreateTeamReq, token : String? = SingletonObject.token) : Response<Team>
    suspend fun getTeamInfo(id:String, token : String? = SingletonObject.token) : Response<Team>
    suspend fun deleteTeam(id:String, token : String? = SingletonObject.token)
}