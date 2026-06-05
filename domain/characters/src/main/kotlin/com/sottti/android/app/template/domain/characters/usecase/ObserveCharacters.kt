package com.sottti.android.app.template.domain.characters.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.characters.model.Character
import kotlinx.coroutines.flow.Flow

public fun interface ObserveCharacters {
    public operator fun invoke(): Flow<PagingData<Character>>
}
