package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.config.Constants
import com.ccm.lib.flexiblewebview.settings.CacheMode
import kotlinx.android.synthetic.main.activity_demo.*

class DemoWebViewActivity : AppCompatActivity() {

    private val TAG = DemoWebViewActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            cacheMode = CacheMode.LOAD_DEFAULT
            animDuration = 500L
            isJavaScriptEnabled = true
            allowHttpMixedContent = true
            circleProgressBar = this@DemoWebViewActivity.progressBar
            webViewContainer = this@DemoWebViewActivity.webViewContainer
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
