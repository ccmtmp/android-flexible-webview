package com.ccm.lib.flexiblewebview.sample.demo

import android.os.Bundle
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.sample.BaseActivity
import com.ccm.lib.flexiblewebview.sample.R
import com.ccm.lib.flexiblewebview.sample.config.Constants
import kotlinx.android.synthetic.main.activity_noraml_progress_bar.*

class NormalProgressBarWebViewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noraml_progress_bar)

        flexibleWebView = FlexibleWebView(this).apply {
            progressBar = this@NormalProgressBarWebViewActivity.progressBar
            webViewContainer = this@NormalProgressBarWebViewActivity.webViewContainer
            setWebView(webView)
            loadUrl(Constants.DEMO_URL)
        }
    }
}
