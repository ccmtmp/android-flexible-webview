package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.config.Constants
import kotlinx.android.synthetic.main.activity_simple.*

class SimpleWebViewActivity : AppCompatActivity() {

    private val TAG = SimpleWebViewActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        flexibleWebView = FlexibleWebView(this).apply {
            isJavaScriptEnabled = true
            webViewContainer = this@SimpleWebViewActivity.webViewContainer
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
