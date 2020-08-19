package com.ccm.lib.flexiblewebview.demo

import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.clients.FlexibleChromeClient
import com.ccm.lib.flexiblewebview.clients.FlexibleWebViewClient
import com.ccm.lib.flexiblewebview.config.Constants
import com.ccm.lib.flexiblewebview.settings.CacheMode
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar
import kotlinx.android.synthetic.main.activity_demo.*

class DemoWebViewActivity : AppCompatActivity() {

    private val TAG = DemoWebViewActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            cacheMode = CacheMode.LOAD_NO_CACHE
            animDuration = 700L
            isJavaScriptEnabled = true
            isJavaScriptCanOpenWindowsAutomatically = false
            allowHttpMixedContent = false
            webViewContainer = this@DemoWebViewActivity.webViewContainer

            /* Optional to set these clients */
            customWebViewClient = CustomFlexibleWebViewClient(this@DemoWebViewActivity)
            customWebChromeClient = CustomFlexibleChromeClient(this@DemoWebViewActivity.progressBar)

            /* If ProgressBar is set to customWebChromeClient, can skip this line */
            circleProgressBar = this@DemoWebViewActivity.progressBar

            setWebView(webView)

            loadUrl(Constants.DEMO_URL)
        }
    }

    private inner class CustomFlexibleWebViewClient(
        context: Context?,
        cacheAppCookie: Boolean = true
    ) :
        FlexibleWebViewClient(context, cacheAppCookie) {
    }

    private inner class CustomFlexibleChromeClient(
        circleProgressBar: CircleProgressBar?,
        progressBar: ProgressBar? = null
    ) : FlexibleChromeClient(circleProgressBar, progressBar) {
    }

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}
