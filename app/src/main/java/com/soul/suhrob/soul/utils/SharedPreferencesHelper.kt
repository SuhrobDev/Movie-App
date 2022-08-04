package com.soul.suhrob.soul.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.soul.suhrob.soul.utils.Constants.APP_PREFS_NAME

/**
 * Created by Microstar on 02.06.2020.
 */
class SharedPreferencesHelper(var context: Context) {

    private var preferences: SharedPreferences

    private lateinit var editor: SharedPreferences.Editor

    init {
//        preferences = context.getSharedPreferences(APP_PREFS_NAME, MODE_PRIVATE)
        val masterKey = MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()
        preferences = EncryptedSharedPreferences.create(
            context,
            APP_PREFS_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun setToken(token: String) {
        editor = preferences.edit()
        editor.putString("TOKEN", token)
        editor.apply()
    }

    fun getToken() = preferences.getString("TOKEN", "empty")

}