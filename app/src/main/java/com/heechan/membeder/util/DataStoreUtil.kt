package com.heechan.membeder.util

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.heechan.membeder.model.data.auth.LoginReq
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "dataStore")

class DataStoreUtil(private val context: Context) {

    companion object {
        val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

    val accessToken = context.dataStore.data.map {
        it[KEY_ACCESS_TOKEN] ?: ""
    }

    suspend fun setAccessToken(accessToken: String) {
        context.dataStore.edit {
            it[KEY_ACCESS_TOKEN] = accessToken
        }
    }
}