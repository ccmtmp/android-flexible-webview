package com.ccm.lib.flexiblewebview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ccm.lib.flexiblewebview.demo.DemoWebViewActivity
import com.ccm.lib.flexiblewebview.demo.NormalProgressBarWebViewActivity
import com.ccm.lib.flexiblewebview.demo.HtmlContentActivity
import com.ccm.lib.flexiblewebview.demo.SimpleWebViewActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDemo.setOnClickListener {
            startActivity(Intent(this, DemoWebViewActivity::class.java))
        }
        btnNormalProgressBar.setOnClickListener {
            startActivity(Intent(this, NormalProgressBarWebViewActivity::class.java))
        }
        btnSimple.setOnClickListener {
            startActivity(Intent(this, SimpleWebViewActivity::class.java))
        }
        btnHtmlContent.setOnClickListener {
            startActivity(Intent(this, HtmlContentActivity::class.java))
        }
    }
}