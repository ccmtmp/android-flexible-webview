package com.ccm.lib.flexiblewebview.clients

import android.content.Context
import android.graphics.Bitmap
import android.webkit.CookieManager
import android.webkit.WebView

/**
 * @param cacheAppCookie: Store app cookie in app. (i.e. login cookie)
 * */
open class CookieWebViewClient(
    context: Context? = null,
    private val cacheAppCookie: Boolean = true
) : HttpAuthWebViewClient(context) {

    private var loginCookie: String? = null

    var callback: FlexibleCallback? = null

    interface FlexibleCallback {
        fun retrieveCookie(cookieString: String)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        if (cacheAppCookie) {
            val cookieManager = CookieManager.getInstance()
            val cookieString = cookieManager.getCookie(url)
            if (!cookieString.isNullOrBlank()) {
                callback?.retrieveCookie(cookieString)
            }
        }
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        if (cacheAppCookie) {
            val cookieManager = CookieManager.getInstance()
            cookieManager.setCookie(url, loginCookie)
            view?.invalidate()
        }
        super.onPageFinished(view, url)
    }

    override fun onLoadResource(view: WebView?, url: String?) {
        if (cacheAppCookie) {
            val cookieManager = CookieManager.getInstance()
            loginCookie = cookieManager.getCookie(url)
        }
        super.onLoadResource(view, url)
    }
}