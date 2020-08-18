package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.config.Constants
import com.ccm.lib.flexiblewebview.data.WebContent
import com.ccm.lib.flexiblewebview.settings.CacheMode
import kotlinx.android.synthetic.main.activity_demo.*

class HtmlContentActivity : AppCompatActivity() {

    private val TAG = HtmlContentActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            cacheMode = CacheMode.LOAD_DEFAULT
            animDuration = 500L
            isJavaScriptEnabled = true
            allowHttpMixedContent = true
            circleProgressBar = this@HtmlContentActivity.progressBar
            webViewContainer = this@HtmlContentActivity.webViewContainer
            setWebView(webView)
            loadWebContent(
                WebContent(
                    Constants.BASE_URL,
                    Constants.HTML_TEMPLATE,
                    Constants.HTML_CONTENT
                )
            )
        }
    }

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}
