package com.ccm.lib.flexiblewebview.sample.demo

import android.os.Bundle
import android.util.Log
import android.webkit.JsResult
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.ccm.lib.flexiblewebview.Constants
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.clients.FlexibleChromeClient
import com.ccm.lib.flexiblewebview.sample.BaseActivity
import com.ccm.lib.flexiblewebview.sample.R
import kotlinx.android.synthetic.main.activity_demo.*

class JavascriptEvaluationActivity : BaseActivity() {

    companion object {
        const val handleAlertInChromeClient = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            isJavaScriptEnabled = true  /* actually, its default set to true in FlexibleWebView */
            isJavaScriptCanOpenWindowsAutomatically = true  /* Optional*/
            if (handleAlertInChromeClient) {
                customWebChromeClient = CustomFlexibleChromeClient()
            }

            setWebView(this@JavascriptEvaluationActivity.webView)
            loadUrl("file:///android_asset/demo_javascript_evaluation.html")
        }

        jsEvaluation.isVisible = true
        btnSend.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage() {
        flexibleWebView.getCustomWebView()
            .evaluateJavascript("show(\"${etText.text}\")") { value ->
                Log.d(TAG, "callback: value=$value")
            }
    }

    private inner class CustomFlexibleChromeClient : FlexibleChromeClient() {
        override fun onJsAlert(
            view: WebView?,
            url: String?,
            message: String?,
            result: JsResult?
        ): Boolean {
            Log.d(Constants.TAG, "url=$url, message=$message, result=$result")

            val builder = AlertDialog.Builder(this@JavascriptEvaluationActivity).apply {
                setTitle("Alert")
                setMessage(message)
                setPositiveButton(android.R.string.ok) { _, _ ->
                    result?.confirm()
                }
                setCancelable(false)
            }
            builder.create().show()

            return true
        }
    }
}