package com.geektech.newsapp45

import android.content.Context
import android.net.Uri

class Prefs(context: Context) {

    private val preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE)

    fun saveBoardState() {
        preferences.edit()
            .putBoolean("isShown", true)
            .apply()
    }

    fun isShown(): Boolean {
        return preferences.getBoolean("isShown", false)
    }


    fun setImage(url: String) {
        preferences.edit().putString("avatar", url).apply()
    }

    fun getImage() : String? {
        return preferences.getString("avatar", null)
    }
}