package com.ccm.lib.flexiblewebview

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.ccm.lib.flexiblewebview.clients.FlexibleChromeClient
import com.ccm.lib.flexiblewebview.clients.FlexibleWebViewClient
import com.ccm.lib.flexiblewebview.data.WebContent
import com.ccm.lib.flexiblewebview.settings.CacheMode
import com.ccm.lib.flexiblewebview.utils.DEFAULT_ENCODING
import com.ccm.lib.flexiblewebview.utils.DEFAULT_MIME_TYPE
import com.ccm.lib.flexiblewebview.utils.atLeastApi21
import com.ccm.lib.flexiblewebview.utils.getDefaultUserAgent
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar

class FlexibleWebView(
    private val context: Context? = null,
    private val acceptCookie: Boolean = true
) {

    private val TAG = FlexibleWebView::class.java.simpleName

    var cacheMode = CacheMode.LOAD_DEFAULT
    var allowHttpMixedContent = false
    var isJavaScriptEnabled = false
    var allowAutoMediaPlayback = false
    var userAgent: String? = null

    private lateinit var webView: WebView
    var customWebViewClient: WebViewClient? = null
    var customWebChromeClient: WebChromeClient? = null
    var circleProgressBar: CircleProgressBar? = null
    var progressBar: ProgressBar? = null

    var webViewContainer: ViewGroup? = null
    var animDuration = 500L
    var onWebViewDisplayCallback: Runnable? = null

    init {
        CookieManager.getInstance().setAcceptCookie(acceptCookie)
    }

    fun setDefaultUserAgentWithSuffix(suffix: String) {
        userAgent = context?.getDefaultUserAgent()
        this.userAgent = "$userAgent $suffix"
    }

    fun setWebView(webView: WebView) {
        this.webView = webView

        /**
         * Starting from Android 5.0, can not store third-party cookies with WebView.
         * Enable third-party cookie support can synchronize cookies automatically to solve verification issue of login.
         * */
        if (atLeastApi21()) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
        }
    }

    private fun initWebViewSettings() {
        if (!this::webView.isInitialized) {
            throw IllegalArgumentException("WebView is not initialized before loading web content.")
        }

        this.webView.settings.apply {
            if (userAgent != null) {
                userAgentString = userAgent
            }

            /**
             * Starting from android 5.0, default is MIXED_CONTENT_NEVER_ALLOW.
             * So, WebView is always not allowed to load Https and Http at the same time start.
             * But if want to support both HTTPS and HTTP, can set it to be MIXED_CONTENT_ALWAYS_ALLOW.
             * Remark: MIXED_CONTENT_NEVER_ALLOW is much security than MIXED_CONTENT_ALWAYS_ALLOW
             * */
            if (allowHttpMixedContent) {
                if (atLeastApi21()) {
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                }
            }

            javaScriptEnabled = isJavaScriptEnabled

            /**
             * After Api Level 17, user's gestures are required to play the sound of the web page loaded by WebView.
             * Default value is true.
             * Set false to play automatically, otherwise, gestures will be required to play the trigger sound effect.
             * */
            mediaPlaybackRequiresUserGesture = !allowAutoMediaPlayback
        }

        updateWebClient()
        updateWebChromeClient()
    }

    private fun updateWebClient() {
        val client =
            if (customWebViewClient != null) customWebViewClient else FlexibleWebViewClient(
                context,
                webView
            )
        if (client != null) {
            webView.webViewClient = client
        }
    }

    private fun updateWebChromeClient() {
        val client =
            if (customWebChromeClient != null) customWebChromeClient else FlexibleChromeClient(
                circleProgressBar, progressBar
            )
        if (client != null) {
            webView.webChromeClient = client
        }
    }

    fun loadUrl(url: String, additionalHttpHeaders: Map<String, String> = mapOf()) {
        initWebViewSettings()
        if (additionalHttpHeaders.isEmpty()) {
            webView.loadUrl(url)
        } else {
            webView.loadUrl(url, additionalHttpHeaders)
        }

        startDisplayAnimation(webViewContainer, animDuration, onWebViewDisplayCallback)
    }

    fun loadWebContent(webContent: WebContent) {
        initWebViewSettings()
        webView.loadDataWithBaseURL(
            webContent.baseUrl,
            webContent.getRenderedHtml(),
            DEFAULT_MIME_TYPE,
            DEFAULT_ENCODING,
            webContent.historyUrl
        )

        startDisplayAnimation(webViewContainer, animDuration, onWebViewDisplayCallback)
    }

    private fun startDisplayAnimation(
        viewGroup: ViewGroup? = null,
        animationDuration: Long,
        callback: Runnable? = null
    ) {
        viewGroup?.apply {
            isVisible = true
            alpha = 0F
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    ViewCompat.animate(viewGroup).setDuration(animationDuration).alpha(1F)
                        .withEndAction(callback)
                },
                80
            )
        }
    }

    fun handledBack(): Boolean {
        if (webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return false
    }
}