package com.sottti.android.app.template.domain.characters.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.characters.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

public class ObserveCharactersFake : ObserveCharacters {

    private var charactersFlow: Flow<PagingData<Character>> = flowOf(PagingData.empty())

    public fun setCharacters(flow: Flow<PagingData<Character>>) {
        charactersFlow = flow
    }

    override operator fun invoke(): Flow<PagingData<Character>> = charactersFlow
}
