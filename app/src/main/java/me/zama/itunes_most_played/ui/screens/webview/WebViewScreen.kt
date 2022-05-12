package me.zama.itunes_most_played.ui.screens.webview

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import java.net.URLDecoder

@Composable
fun WebViewScreen(url: String) {
    val state = rememberWebViewState(url = URLDecoder.decode(url, "utf-8"))
    WebView(
        state = state,
        onCreated = {
            @SuppressLint("SetJavaScriptEnabled")
            it.settings.javaScriptEnabled = true
        }
    )
}