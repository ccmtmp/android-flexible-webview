package com.ccm.lib.flexiblewebview.clients

import android.content.Context
import android.view.LayoutInflater
import android.webkit.HttpAuthHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.ccm.lib.flexiblewebview.R
import java.lang.ref.WeakReference

open class HttpAuthWebViewClient(
    private var context: Context? = null
) : WebViewClient() {

    private val contextRef = WeakReference<Context>(context)

    /**
     * If need proceed with authentication with your credentials, just set your user name and password here.
     *
     * Same implementation with `Basic Authentication Header`:
     * - `Authorization: Basic WU9VUl9VU0VSTkFNRTpZT1VSX1BBU1NXT1JE`
     *
     * Remark: Security Encapsulation of name and password should be handled by you.
     * */
    var userName: String? = null
    var userPass: String? = null

    init {
        context = contextRef.get()
    }

    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        if (!userName.isNullOrEmpty() || !userPass.isNullOrEmpty()) {
            handler?.proceed(userName, userPass)
            super.onReceivedHttpAuthRequest(view, handler, host, realm)
        } else {
            showHttpAuthDialog(view, handler, host, realm)
        }
    }

    private fun showHttpAuthDialog(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        val context = context ?: return

        val dialogContainer = LayoutInflater.from(context).inflate(R.layout.view_http_auth, null)
        val httpAuthDialog = AlertDialog.Builder(context)
        httpAuthDialog
            .setTitle("Basic Authentication")
            .setView(dialogContainer)
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                val userName =
                    dialogContainer.findViewById<EditText>(R.id.etUserName).text.toString()
                val userPass =
                    dialogContainer.findViewById<EditText>(R.id.etUserPass).text.toString()
                handler?.proceed(userName, userPass)
                super.onReceivedHttpAuthRequest(view, handler, host, realm)
            }
            .setNegativeButton("Cancel") { _, _ ->
                handler?.cancel()
                super.onReceivedHttpAuthRequest(view, handler, host, realm)
            }
            .create()
            .show()
    }
}