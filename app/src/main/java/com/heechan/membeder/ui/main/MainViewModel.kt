package com.heechan.membeder.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.heechan.membeder.model.data.SingletonObject
import com.heechan.membeder.model.data.team.Team
import com.heechan.membeder.model.data.team.TeamListRes
import com.heechan.membeder.model.remote.AuthRepositoryImpl
import com.heechan.membeder.model.remote.TeamRepository
import com.heechan.membeder.model.remote.TeamRepositoryImpl
import com.heechan.membeder.util.State
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {
    private val teamRepository = TeamRepositoryImpl()
    private val authRepository = AuthRepositoryImpl()

    val state = MutableLiveData<State>()

    val teamList = MutableLiveData<TeamListRes>()

    val userData = SingletonObject.userData

    fun getTeamData() {
        if (state.value == State.LOADING) {
            return
        }

        viewModelScope.launch(
            CoroutineExceptionHandler { _, e ->
                Log.e("[TeamBuilding]", e.toString())

                state.value = State.FAIL
            }
        ) {
            state.value = State.LOADING

            val response = withContext(Dispatchers.IO) {
                teamRepository.getTeamList(SingletonObject.token.value!!)
            }

            if (response.isSuccessful && response.body() != null) {
                teamList.value = response.body()
                state.value = State.SUCCESS
            } else {
                Log.e("[TeamBuilding]", response.errorBody().toString())

                state.value = State.FAIL
            }
        }
    }

    fun homeRefresh() {
        viewModelScope.launch(CoroutineExceptionHandler{ _, e ->
            state.value = State.FAIL
        }) {
            state.value = State.LOADING

            val response = withContext(Dispatchers.IO) {
                authRepository.getLoginUser()
            }

            if(response.isSuccessful && response.body() != null) {
                val body = response.body()!!

                SingletonObject.userData.value = body.user
                SingletonObject.selectTeam.value = body.user.teamList[0]
                state.value = State.SUCCESS
            }
            else {
                state.value = State.FAIL
            }
        }
    }
}