package com.example.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 *    author : LIU YANG
 *    date   : 2024/8/20
 *    desc   : dataStoreUtil
 */

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "myPreferences")



val Context.userInfoStore: DataStore<User> by dataStore(fileName = "userInfo.pb", serializer = UserSerializer
)
