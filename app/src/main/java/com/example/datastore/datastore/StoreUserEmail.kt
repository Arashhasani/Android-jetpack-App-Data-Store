package com.example.datastore.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class StoreUserEmail(private val context: Context) {


    companion object{
        private val Context.dataStore by preferencesDataStore("UserEmail")
        val USER_EMAIL_KEY= stringPreferencesKey("user_email")
    }


    val getEmai: Flow<String?> = context.dataStore.data.map {preference->
        preference[USER_EMAIL_KEY]
    }

    suspend fun saveEmail(name:String){
        context.dataStore.edit {preference->
        preference[USER_EMAIL_KEY]=name
        }
    }
}
