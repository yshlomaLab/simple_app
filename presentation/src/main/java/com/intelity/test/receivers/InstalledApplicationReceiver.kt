package com.intelity.test.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.intelity.test.Const

/**
 * Receiver to track installed applications.
 */
class InstalledApplicationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val packageName = intent?.data?.encodedSchemeSpecificPart
        val sharedPreferences = context?.getSharedPreferences(Const.SHARED_PREFS, Context.MODE_PRIVATE)
        val set = sharedPreferences?.getStringSet(Const.PACKAGE_ADDED, mutableSetOf())
        set?.add(packageName)
        sharedPreferences?.edit()?.putStringSet(Const.PACKAGE_ADDED, set)?.apply()
    }
}