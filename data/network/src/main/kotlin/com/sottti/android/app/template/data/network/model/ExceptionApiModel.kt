package com.sottti.android.app.template.data.network.model

public sealed class ExceptionApiModel(
    override val message: String,
) : Exception(message) {

    public data class ClientError(
        override val message: String,
        val code: Int,
        val errorBody: String?,
    ) : ExceptionApiModel(message)

    public data class NoInternet(
        override val message: String,
    ) : ExceptionApiModel(message)

    public data class RedirectError(
        override val message: String,
        val code: Int,
    ) : ExceptionApiModel(message)

    public data class ServerError(
        override val message: String,
        val code: Int,
    ) : ExceptionApiModel(message)

    public data class Timeout(
        override val message: String,
    ) : ExceptionApiModel(message)

    public data class Unknown(
        override val message: String,
    ) : ExceptionApiModel(message)
}
