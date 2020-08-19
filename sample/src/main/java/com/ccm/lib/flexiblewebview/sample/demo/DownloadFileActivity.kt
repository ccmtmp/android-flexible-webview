package com.ccm.lib.flexiblewebview.sample.demo

import android.os.Bundle
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.sample.BaseActivity
import com.ccm.lib.flexiblewebview.sample.R
import com.ccm.lib.flexiblewebview.sample.config.Constants
import kotlinx.android.synthetic.main.activity_demo.*

class DownloadFileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            circleProgressBar = this@DownloadFileActivity.progressBar
            setWebView(this@DownloadFileActivity.webView)
            loadUrl(Constants.DEMO_URL_DOWNLOAD_FILE)
        }
    }
}