package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import kotlinx.android.synthetic.main.activity_demo.*

class JavascriptInterfaceActivity : AppCompatActivity() {

    companion object {
        const val JS_TAG = "javascript_object"
    }

    private val TAG = JavascriptInterfaceActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            isJavaScriptEnabled = true  /* actually, its default set to true in FlexibleWebView */
            setWebView(this@JavascriptInterfaceActivity.webView)
            loadUrl("file:///android_asset/demo_javascript_interface.html")

            getCustomWebView().addJavascriptInterface(JavascriptInterface(), JS_TAG)
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

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}