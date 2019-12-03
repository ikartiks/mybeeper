package com.mybeepertest

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.os.IBinder
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun showCustomMessage(message: String) {

        val adb = AlertDialog.Builder(this)
        adb.setTitle(this.resources.getString(R.string.app_name))
        adb.setMessage(message)
        adb.setPositiveButton("OK", { dialog, which -> dialog.dismiss() })

        adb.create().show()
    }

    fun putBoolean(key: String, value: Boolean) {

        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        val preferencesName = this.resources.getString(R.string.app_name)
        val sharedPreferences = this.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defaultValue)
    }
}