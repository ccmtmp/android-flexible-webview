# Flexible Webview

[![CI](https://github.com/ccmtmp/flexible-webview/workflows/CI/badge.svg)](https://github.com/ccmtmp/flexible-webview/actions?query=workflow%3ACI)
[![GitHub release](https://img.shields.io/github/release/ccmtmp/flexible-webview.svg?maxAge=60)](https://github.com/ccmtmp/flexible-webview/releases)
[![License](https://img.shields.io/github/license/ccmtmp/flexible-webview)](https://github.com/ccmtmp/flexible-webview/blob/master/LICENSE)

The Flexible WebView library is designed to centralize all webview-related features. You can build fully custom experiences.


Table of contents
=================

<!--ts-->
   * [Installation](#installation)
      * [Requirements](#requirements)
      * [Configuration](#configuration)
   * [Features](#features)
   * [Getting Started](#getting-started)
<!--te-->

## Installation

### Requirements

* Android 5.0 (API level 21) and above
* [Android Gradle Plugin](https://developer.android.com/studio/releases/gradle-plugin) 4.0+
* [AndroidX](https://developer.android.com/jetpack/androidx/)

### Configuration

Add `flexible-webview` to your `build.gradle` dependencies.

```
dependencies {
    implementation 'com.github.ccmtmp:flexible-webview:2.0.8'
}
```

## Features

**Set Authentication Header**: If need to proceeding with authentication with your credentials, set user name and password to `FlexibleWebViewClient`.
```kotlin
val webViewClient = FlexibleWebViewClient(context).apply {
    userName = "YOUR_AUTH_USERNAME"
    userPass = "YOUR_AUTH_PASSWORD"
}
```

**Cache Cookie**: Default storing cookie (i.e. login cookie) for your WebView passed. Can disable on `FlexibleWebViewClient`: 
```kotlin
val webViewClient = FlexibleWebViewClient(context).apply {
    cacheAppCookie = false
}
```
Same implementation with Basic Authentication Header: `Authorization: Basic WU9VUl9VU0VSTkFNRTpZT1VSX1BBU1NXT1JE`

**Load HTML File or Template**: Support loading a HTML file or HTML template string integrating with content or not.
```kotlin
loadUrl("file:///android_asset/demo.html")
```
```kotlin
loadWebContent(
    WebContent(
        Constants.BASE_URL,
        Constants.HTML_TEMPLATE,
        Constants.HTML_CONTENT
    )
)
```

**URL Scheme**: Default handle scheme like: `tel:`, `sms:`, `mailto:`, etc. Can disable on `FlexibleWebViewClient`: 
```kotlin
val flexibleWebView = FlexibleWebView(context).apply {
    handleUrlScheme = false
}
```

**Javascript Interface and Evaluation**: Not customize in this library, just give a demo to for these embeded features. Can override `FlexibleChromeClient` to handle javascript alert. And remember to enable javascript (`isJavaScriptEnabled = true`).
```kotlin
val flexibleWebView = FlexibleWebView(context).apply {
    isJavaScriptEnabled = true
    isJavaScriptCanOpenWindowsAutomatically = true  /* Optional*/
}
```

**Download File**: Default allowing to download file from WebView. Can disable by:
```kotlin
val flexibleWebView = FlexibleWebView(context).apply {
    allowDownloadFile = false
}
```

**Set Loading View**: Can set `CircleProgressBar` or normal `ProgressBar` if want. 

**Retreive input WebView**: Use method `FlexibleWebView.getCustomWebView()`.

## Getting Started

Get started with [sample projects](https://github.com/ccmtmp/flexible-webview/tree/master/sample).

The [DemoWebViewActivity](https://github.com/ccmtmp/flexible-webview/blob/master/sample/src/main/java/com/ccm/lib/flexiblewebview/sample/demo/DemoWebViewActivity.kt) class is the entry-point and contains most of available settings for WebView. It will let you have an overview of this library.

Other classes will bring you a deep look on other WebView features.

If any issues when using this libray, can try to take a look on debug log using logcat tag: `FlexibleWebView`.
