package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.config.Constants
import kotlinx.android.synthetic.main.activity_demo.*

class DownloadFileActivity : AppCompatActivity() {

    private val TAG = DownloadFileActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            setWebView(this@DownloadFileActivity.webView)
            loadUrl(Constants.DEMO_URL_DOWNLOAD_FILE)
        }
    }

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}