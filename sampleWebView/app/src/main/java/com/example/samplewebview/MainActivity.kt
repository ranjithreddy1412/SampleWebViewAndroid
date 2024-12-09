package com.example.samplewebview

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.samplewebview.ui.theme.SampleWebViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SampleWebViewTheme {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Pay by Bank",
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.clickable {
                            openWebView()
                        }
                    )
                }
            }
        }
    }

    private fun openWebView() {
        val webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mixedContentMode = android.webkit.WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.settings.cacheMode = android.webkit.WebSettings.LOAD_NO_CACHE
        webView.clearCache(true)
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val fixedUrl = url.replace("http://localhost:3000", "http://10.0.2.2:3000")
                view.loadUrl(fixedUrl)
                return true
            }
        }
        webView.loadUrl("http://10.0.2.2:3000")
        setContentView(webView)

    }


}
