package com.ccm.lib.flexiblewebview.sample

import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val TAG = "FlexibleWebViewSample"
    }

    protected lateinit var flexibleWebView: FlexibleWebView

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}