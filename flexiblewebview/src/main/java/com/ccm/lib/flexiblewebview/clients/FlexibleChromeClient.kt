package com.ccm.lib.flexiblewebview.clients

import android.os.Build
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar

open class FlexibleChromeClient(
    private val circleProgressBar: CircleProgressBar? = null,
    private val progressBar: ProgressBar? = null
) : WebChromeClient() {

    private val circleProgressFadTime = 0

    init {
        circleProgressBar?.apply {
            setCircleBackgroundEnabled(true)
            isVisible = true
            isShowProgressText = true
        }
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        if (circleProgressBar != null) {
            if (newProgress < 100) {
                circleProgressBar.progress = newProgress
                if (!circleProgressBar.isVisible) {
                    circleProgressBar.isVisible = true
                }
            } else {
                if (circleProgressFadTime > 0) {
                    ViewCompat.animate(circleProgressBar).alpha(0F).withEndAction {
                        circleProgressBar.isVisible = false
                    }
                } else {
                    circleProgressBar.isVisible = false
                }
            }
        } else {
            if (newProgress < 100) {
                progressBar?.apply {
                    isVisible = true
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        setProgress(newProgress, true)
                    } else {
                        progress = newProgress
                    }
                }
            } else {
                progressBar?.isVisible = false
            }
        }
        super.onProgressChanged(view, newProgress)
    }
}