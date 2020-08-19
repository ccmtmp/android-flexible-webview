package com.ccm.lib.flexiblewebview.sample.demo

import android.content.Context
import android.os.Bundle
import android.widget.ProgressBar
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.clients.FlexibleChromeClient
import com.ccm.lib.flexiblewebview.clients.FlexibleWebViewClient
import com.ccm.lib.flexiblewebview.sample.BaseActivity
import com.ccm.lib.flexiblewebview.sample.R
import com.ccm.lib.flexiblewebview.sample.config.Constants
import com.ccm.lib.flexiblewebview.settings.CacheMode
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar
import kotlinx.android.synthetic.main.activity_demo.*

class DemoWebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            cacheMode = CacheMode.LOAD_DEFAULT
            animDuration = 600L
            isJavaScriptEnabled = true
            isJavaScriptCanOpenWindowsAutomatically = false
            allowHttpMixedContent = true
            webViewContainer = this@DemoWebViewActivity.webViewContainer

            /* Optional to set these clients */
            customWebViewClient = CustomFlexibleWebViewClient(this@DemoWebViewActivity).apply {
                // Optional to set name and password if has authentication
                userName = ""
                userPass = ""

                // Optional settings
                cacheAppCookie = false
                handleUrlScheme = false
                allowDownloadFile = false
            }
            customWebChromeClient = CustomFlexibleChromeClient(this@DemoWebViewActivity.progressBar)

            /* If ProgressBar is set to customWebChromeClient, can skip this line */
            circleProgressBar = this@DemoWebViewActivity.progressBar

            setWebView(webView)

            loadUrl(Constants.DEMO_URL)
        }
    }

    private inner class CustomFlexibleWebViewClient(context: Context? = null) :
        FlexibleWebViewClient(context) {
    }

    private inner class CustomFlexibleChromeClient(
        circleProgressBar: CircleProgressBar? = null,
        progressBar: ProgressBar? = null
    ) : FlexibleChromeClient(circleProgressBar, progressBar) {
    }
}
