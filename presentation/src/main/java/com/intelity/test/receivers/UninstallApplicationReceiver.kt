package com.intelity.test.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.intelity.test.Const

/**
 * Receiver to track uninstalled applications.
 */
class UninstallApplicationReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val uri = intent?.data
        val packageName = uri?.schemeSpecificPart
        val sharedPreferences = context?.getSharedPreferences(Const.SHARED_PREFS, Context.MODE_PRIVATE)
        val set = sharedPreferences?.getStringSet(Const.PACKAGE_REMOVED, mutableSetOf())
        set?.add(packageName)
        sharedPreferences?.edit()?.putStringSet(Const.PACKAGE_REMOVED, set)?.apply()
    }
}