package com.ccm.lib.flexiblewebview.settings

sealed class CacheMode {
    object LOAD_DEFAULT : CacheMode()
    object LOAD_NO_CACHE : CacheMode()
}