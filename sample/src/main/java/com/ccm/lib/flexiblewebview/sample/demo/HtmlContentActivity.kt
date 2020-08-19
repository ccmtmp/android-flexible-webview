package com.ccm.lib.flexiblewebview.sample.demo

import android.os.Bundle
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.data.WebContent
import com.ccm.lib.flexiblewebview.sample.BaseActivity
import com.ccm.lib.flexiblewebview.sample.R
import com.ccm.lib.flexiblewebview.sample.config.Constants
import kotlinx.android.synthetic.main.activity_demo.*

class HtmlContentActivity : BaseActivity() {

    companion object {
        const val loadLocalHtml = true
    }

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
}
