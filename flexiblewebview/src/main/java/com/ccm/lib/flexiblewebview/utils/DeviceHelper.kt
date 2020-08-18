package com.ccm.lib.flexiblewebview.utils

import android.os.Build

fun atLeastApi21() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun atLeastApi23() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun atLeastApi29() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q