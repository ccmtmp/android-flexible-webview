package com.ccm.lib.flexiblewebview.demo

import android.os.Bundle
import android.util.Log
import android.webkit.JsResult
import android.webkit.WebView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.ccm.lib.flexiblewebview.Constants
import com.ccm.lib.flexiblewebview.FlexibleWebView
import com.ccm.lib.flexiblewebview.R
import com.ccm.lib.flexiblewebview.clients.FlexibleChromeClient
import kotlinx.android.synthetic.main.activity_demo.*

class JavascriptEvaluationActivity : AppCompatActivity() {

    companion object {
        const val showAlertInChromeClient = false
    }

    private val TAG = JavascriptEvaluationActivity::class.java.simpleName
    private lateinit var flexibleWebView: FlexibleWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)

        flexibleWebView = FlexibleWebView(this).apply {
            isJavaScriptEnabled = true  /* actually, its default set to true in FlexibleWebView */
            isJavaScriptCanOpenWindowsAutomatically = true
            if (showAlertInChromeClient) {
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
            .evaluateJavascript("javascript:show(\"${etText.text}\")") { value ->
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

    override fun onBackPressed() {
        if (this::flexibleWebView.isInitialized && flexibleWebView.handledBack()) {
            return
        }
        super.onBackPressed()
    }
}