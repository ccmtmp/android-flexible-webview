package com.ccm.lib.flexiblewebview.data

import com.ccm.lib.flexiblewebview.utils.mergeWithHtmlTemplate
import org.jsoup.Jsoup

/**
 * @param htmlTemplate
 * It is a HTML Template string including <html>, <head>, <body>.
 * Example: "<!DOCTYPE html><html><head> YOUR_SETTING (e.g. <meta>) </head><body> YOUR_BODY </body></html>"
 * Can add `charset`, `css`, etc. settings to <meta>
 *
 * @param htmlContent
 * htmlContent can be whole HTML content or to be integrating with htmlTemplate.
 * */
class WebContent(
    val baseUrl: String = "",
    private var htmlTemplate: String = "",
    private val htmlContent: String = "",
    val historyUrl: String = ""
) {

    fun appendCss(cssBody: String): WebContent {
        val document = Jsoup.parse(htmlTemplate)
        document.head().append("<style type=\"text/css\">$cssBody</style>")
        htmlTemplate = document.outerHtml()
        return this
    }

    fun getRenderedHtml(): String {
        if (htmlTemplate.isEmpty()) {
            return htmlContent
        }
        return htmlContent.mergeWithHtmlTemplate(htmlTemplate)
    }

    override fun toString(): String {
        return getRenderedHtml()
    }
}
