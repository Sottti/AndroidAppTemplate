package com.sottti.android.app.template.usecase

import androidx.paging.PagingData
import com.sottti.android.app.template.model.Item
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.channels.BufferOverflow

public class FakeObserveItems : ObserveItems {

    private val subject = MutableSharedFlow<PagingData<Item>>(replay = 1, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    init {
        subject.tryEmit(PagingData.empty())
    }

    public fun tryEmit(paging: PagingData<Item>) {
        subject.tryEmit(paging)
    }

    public suspend fun emit(paging: PagingData<Item>) {
        subject.emit(paging)
    }

    override operator fun invoke(): Flow<PagingData<Item>> = subject
}
