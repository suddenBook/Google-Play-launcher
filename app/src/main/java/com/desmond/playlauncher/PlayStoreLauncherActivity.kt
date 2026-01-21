package com.desmond.playlauncher

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle

class PlayStoreLauncherActivity : Activity() {

    private var shouldFinishOnResume = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (tryLaunchPlayStore()) {
            shouldFinishOnResume = true
        } else {
            setContentView(R.layout.activity_play_store_launcher)
        }
    }

    override fun onResume() {
        super.onResume()
        if (shouldFinishOnResume) {
            finish()
        }
    }

    private fun tryLaunchPlayStore(): Boolean {
        val launchIntent = resolvePlayStoreIntent() ?: return false
        return try {
            startActivity(launchIntent)
            true
        } catch (e: ActivityNotFoundException) {
            false
        }
    }

    private fun resolvePlayStoreIntent(): Intent? {
        val pm = packageManager
        pm.getLaunchIntentForPackage(PLAY_STORE_PACKAGE)?.let { return it }

        val leanbackIntent = Intent(Intent.ACTION_MAIN)
            .addCategory(Intent.CATEGORY_LEANBACK_LAUNCHER)
            .setPackage(PLAY_STORE_PACKAGE)
        if (leanbackIntent.resolveActivity(pm) != null) {
            return leanbackIntent
        }

        val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(PLAY_STORE_WEB_URL))
        return if (webIntent.resolveActivity(pm) != null) webIntent else null
    }

    companion object {
        private const val PLAY_STORE_PACKAGE = "com.android.vending"
        private const val PLAY_STORE_WEB_URL = "https://play.google.com/store"
    }
}
