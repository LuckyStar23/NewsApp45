package com.geektech.newsapp45

import android.content.Context

class Prefs(context: Context)  {

    private val preferences =
        context.getSharedPreferences("settings", Context.MODE_PRIVATE)


    fun saveBoardState() {
        preferences.edit()
            .putBoolean("isShown", true)
            .apply()
    }

    fun isShown() : Boolean{
        return preferences.getBoolean("isShown", false)
    }
    fun saveImage() {
        preferences.edit()
            .putBoolean("isShown", true)
            .apply()
    }







}