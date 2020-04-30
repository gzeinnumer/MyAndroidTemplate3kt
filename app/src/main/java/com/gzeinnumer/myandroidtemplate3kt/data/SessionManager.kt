package com.gzeinnumer.myandroidtemplate3kt.data

import android.content.Context
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponseLogin

class SessionManager(private val context: Context) {

    companion object {
        private const val PREF_NAME = "session"
        private const val KEY_ID = "id"
        private const val KEY_NAME = "name"
        private const val KEY_USERNAME = "username"
        private const val KEY_EMAIL = "email"
        private const val KEY_WEBSITE = "website"
        private const val KEY_TOKEN = "TOKEN"
    }

    private val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val editor = prefs.edit()

    fun setAuth(data: ResponseLogin) {
        editor.apply{
            putString(KEY_ID, data.id.toString())
            putString(KEY_ID, data.id.toString())
            putString(KEY_USERNAME, data.username)
            putString(KEY_EMAIL, data.email)
            putString(KEY_WEBSITE, data.website)
        }
    }

    val userId: String?
        get() = prefs.getString(KEY_ID, "")

    val token: String?
        get() = prefs.getString(KEY_TOKEN, "")


}