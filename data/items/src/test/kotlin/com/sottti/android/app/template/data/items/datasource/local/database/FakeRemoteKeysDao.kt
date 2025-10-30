package com.sottti.android.app.template.data.items.datasource.local.database

import com.sottti.android.app.template.data.items.datasource.local.model.RemoteKeysRoomModel

internal class FakeRemoteKeysDao : RemoteKeysDao {
    var keys: RemoteKeysRoomModel? = null
    var getCalled = false
    var upsertCalled = false
    var clearCalled = false
    var transactionBlockExecuted = false

    override suspend fun get(anchor: String): RemoteKeysRoomModel? {
        getCalled = true
        return if (anchor == keys?.anchor) keys else null
    }

    override suspend fun upsert(keys: RemoteKeysRoomModel) {
        upsertCalled = true
        this.keys = keys
    }

    override suspend fun clear() {
        clearCalled = true
        keys = null
    }

    override suspend fun withTransaction(block: suspend () -> Unit) {
        transactionBlockExecuted = true
        block()
    }
}
