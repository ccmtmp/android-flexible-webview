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
      * [Releases](#releases)
      * [Proguard](#proguard)
   * [Features](#features)
   * [Getting Started](#getting-started)
   * [Examples](#examples)
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
    implementation 'com.github.ccmtmp:flexible-webview:1.0.0'
}
```

## Features

**Set Authentication Header**: If need proceed with authentication with your credentials, set your user name and password to `HttpAuthWebViewClient`.

```kotlin
val webViewClient = HttpAuthWebViewClient(context, webView).apply {
    userName = "YOUR_AUTH_USERNAME"
    userPass = "YOUR_AUTH_PASSWORD"
}
```
