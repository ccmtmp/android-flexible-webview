package com.ccm.lib.flexiblewebview.utils

import android.content.Context
import android.webkit.WebSettings

fun Context?.getDefaultUserAgent(): String? = try {
    WebSettings.getDefaultUserAgent(this)
} catch (t: Throwable) {
    System.getProperty("http.agent")
}