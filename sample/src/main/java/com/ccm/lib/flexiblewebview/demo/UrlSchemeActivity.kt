package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import kotlinx.android.synthetic.main.activity_demo.*

class UrlSchemeActivity : AppCompatActivity() {

    private val TAG = UrlSchemeActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            setWebView(this@UrlSchemeActivity.webView)
            loadUrl("file:///android_asset/demo_url_scheme.html")
        }
    }

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}