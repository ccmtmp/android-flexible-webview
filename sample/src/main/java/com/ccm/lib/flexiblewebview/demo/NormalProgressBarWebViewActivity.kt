package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.config.Constants
import com.ccm.lib.flexiblewebview.settings.CacheMode
import kotlinx.android.synthetic.main.activity_noraml_progress_bar.*

class NormalProgressBarWebViewActivity : AppCompatActivity() {

    private val TAG = NormalProgressBarWebViewActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noraml_progress_bar)

        flexibleWebView = FlexibleWebView(this).apply {
            cacheMode = CacheMode.LOAD_DEFAULT
            animDuration = 500L
            isJavaScriptEnabled = true
            allowHttpMixedContent = true
            progressBar = this@NormalProgressBarWebViewActivity.progressBar
            webViewContainer = this@NormalProgressBarWebViewActivity.webViewContainer
            setWebView(webView)
            loadUrl(Constants.DEMO_URL)
        }
    }

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}
