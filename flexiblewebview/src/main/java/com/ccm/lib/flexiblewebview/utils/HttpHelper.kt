package com.ccm.lib.flexiblewebview.utils

import android.util.Patterns
import java.util.*

fun String.toWebUrl(): String {
    if (Patterns.WEB_URL.matcher(this.toLowerCase(Locale.getDefault())).matches()) {
        return if (this.contains("http://") || this.contains("https://")) {
            this
        } else {
            "http://$this"
        }
    }
    return this
}