package com.ccm.lib.flexiblewebview.clients

import android.content.Context
import android.net.http.SslError
import android.util.Log
import android.webkit.*
import com.ccm.lib.flexiblewebview.Constants.TAG
import com.ccm.lib.flexiblewebview.utils.atLeastApi21
import com.ccm.lib.flexiblewebview.utils.atLeastApi23

open class FlexibleWebViewClient(
    context: Context? = null,
    webView: WebView? = null,
    cacheAppCookie: Boolean = true,
    hideScrollBar: Boolean = true
) : CookieWebViewClient(context, cacheAppCookie) {

    init {
        if (hideScrollBar) {
            webView?.scrollBarStyle = WebView.SCROLLBARS_OUTSIDE_OVERLAY
        }
    }

    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        super.onReceivedHttpError(view, request, errorResponse)
        if (atLeastApi21()) {
            Log.e(
                TAG,
                "onReceivedHttpError: request=$request; errorResponse=${errorResponse?.reasonPhrase}"
            )
        }
    }

    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        super.onReceivedError(view, errorCode, description, failingUrl)
        Log.e(
            TAG,
            "onReceivedError: errorCode=$errorCode; description=$description; failingUrl=$failingUrl"
        )
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        if (atLeastApi23()) {
            Log.e(
                TAG,
                "onReceivedError: request=$request; error=${error?.description}"
            )
        }
    }

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
        Log.e(
            TAG,
            "onReceivedSslError: error=$error"
        )
    }
}