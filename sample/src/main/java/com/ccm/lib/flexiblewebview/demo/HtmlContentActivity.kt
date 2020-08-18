package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.config.Constants
import com.ccm.lib.flexiblewebview.data.WebContent
import kotlinx.android.synthetic.main.activity_demo.*

class HtmlContentActivity : AppCompatActivity() {

    companion object {
        const val loadLocalHtml = true
    }

    private val TAG = HtmlContentActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            setWebView(webView)

            if (loadLocalHtml) {
                loadUrl("file:///android_asset/demo.html")
//                loadDataWithBaseURL("file:///android_res/mipmap/", Constants.HTML_CONTENT_ASSET)
            } else {
                loadWebContent(
                    WebContent(
                        Constants.BASE_URL,
                        Constants.HTML_TEMPLATE,
                        Constants.HTML_CONTENT
                    )
                )
            }
        }
    }

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}
