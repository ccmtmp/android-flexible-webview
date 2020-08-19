package com.ccm.lib.flexiblewebview.sample.demo

import android.os.Bundle
import android.widget.Toast
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.sample.BaseActivity
import com.ccm.lib.flexiblewebview.sample.R
import kotlinx.android.synthetic.main.activity_demo.*

class JavascriptInterfaceActivity : BaseActivity() {

    companion object {
        const val JS_TAG = "javascript_object"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            isJavaScriptEnabled = true  /* actually, its default set to true in FlexibleWebView */
            setWebView(this@JavascriptInterfaceActivity.webView)
            loadUrl("file:///android_asset/demo_javascript_interface.html")

            getCustomWebView().addJavascriptInterface(
                JavascriptInterface(),
                JS_TAG
            )
        }
    }

    private inner class JavascriptInterface {
        @android.webkit.JavascriptInterface
        fun showToast(text: String?) {
            Toast.makeText(this@JavascriptInterfaceActivity, text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        flexibleWebView.getCustomWebView().removeJavascriptInterface(JS_TAG)
        super.onDestroy()
    }
}