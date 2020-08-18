package com.ccm.lib.flexiblewebview.utils

import org.jsoup.Jsoup

const val DEFAULT_MIME_TYPE = "text/html"
const val DEFAULT_ENCODING = "UTF-8"

fun String.mergeWithHtmlTemplate(template: String?): String {
    template ?: return this
    val document = Jsoup.parse(template)
    document.body().append(this)
    return document.outerHtml()
}