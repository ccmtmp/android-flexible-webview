package com.ccm.lib.flexiblewebview.clients

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.util.Log
import android.webkit.*
import androidx.annotation.RequiresApi
import com.ccm.lib.flexiblewebview.Constants.TAG
import com.ccm.lib.flexiblewebview.utils.atLeastApi21
import com.ccm.lib.flexiblewebview.utils.atLeastApi23

open class FlexibleWebViewClient(
    private val context: Context? = null,
    cacheAppCookie: Boolean = true
) : CookieWebViewClient(context, cacheAppCookie) {

    var handleUrlScheme = true

    /**
     * Handle url scheme: `tel:`, `sms:`, `mailto:`, etc.
     * */
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return if (handleUrlScheme(url)) {
            true
        } else {
            super.shouldOverrideUrlLoading(view, url)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        val url = request?.url.toString()
        return if (handleUrlScheme(url)) {
            true
        } else {
            super.shouldOverrideUrlLoading(view, request)
        }
    }

    private fun handleUrlScheme(url: String?): Boolean {
        if (!handleUrlScheme) {
            return false
        }
        if (URLUtil.isNetworkUrl(url)) {
            return false
        }
        try {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(url)
            }
            context?.startActivity(shareIntent)
            return true
        } catch (e: ActivityNotFoundException) {
            Log.e(TAG, "appropriate app not found: $e")
        } catch (t: Throwable) {
            Log.e(TAG, "unknown exception: $t")
        }
        return false
    }

    /**
     * Print error thrown.
     * */
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