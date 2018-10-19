package com.example.itlab.authenticationfirebaseapp.shared

import android.content.SharedPreferences
import java.util.*

class Preferences internal constructor(private val pref: SharedPreferences) {

    // Primitive

    fun putInt(key: String, value: Int) {
        pref.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, def: Int): Int {
        return pref.getInt(key, def)
    }

    fun putLong(key: String, value: Long) {
        pref.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, def: Long): Long {
        return pref.getLong(key, def)
    }

    fun putFloat(key: String, value: Float) {
        pref.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String, def: Float): Float {
        return pref.getFloat(key, def)
    }

    fun putBoolean(key: String, value: Boolean) {
        pref.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, def: Boolean): Boolean {
        return pref.getBoolean(key, def)
    }

    fun putString(key: String, value: String) {
        pref.edit().putString(key, value).apply()
    }

    fun getString(key: String, def: String): String? {
        return pref.getString(key, def)
    }

    // Date

    fun putDate(key: String, date: Date) {
        pref.edit().putLong(key, date.time).apply()
    }

    fun getDate(key: String): Date {
        return Date(pref.getLong(key, 0))
    }

    fun clear() {
        pref.edit().clear().apply()
    }

}
