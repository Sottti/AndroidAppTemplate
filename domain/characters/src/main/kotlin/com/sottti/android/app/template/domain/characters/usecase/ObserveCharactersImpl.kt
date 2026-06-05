package com.sottti.android.app.template.domain.characters.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.domain.characters.model.Character
import com.sottti.android.app.template.domain.characters.repository.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

public class ObserveCharactersImpl @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ObserveCharacters {
    @OptIn(ExperimentalCoroutinesApi::class)
    public override operator fun invoke(): Flow<PagingData<Character>> =
        charactersRepository.observeCharacters()
}
