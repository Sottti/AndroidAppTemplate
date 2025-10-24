package com.sottti.android.app.template.presentation.navigation.impl

import androidx.navigation3.runtime.NavEntry

internal typealias EntryProvider<K> = (key: K) -> NavEntry<K>
