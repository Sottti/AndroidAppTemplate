package com.sottti.android.app.template.data.network

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.sottti.android.app.template.data.network.model.ExceptionApiModel
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.ClientError
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.NoInternet
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.ServerError
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Timeout
import com.sottti.android.app.template.data.network.model.ExceptionApiModel.Unknown
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.statement.bodyAsText
import java.net.UnknownHostException

public suspend fun <T> safeApiCall(
    apiCall: suspend () -> T,
): Result<T, ExceptionApiModel> = try {
    Ok(apiCall())
} catch (exception: UnknownHostException) {
    Err(NoInternet(exception.message ?: NO_INTERNET_ERROR_MESSAGE))
} catch (exception: SocketTimeoutException) {
    Err(Timeout(exception.message ?: TIMEOUT_ERROR_MESSAGE))
} catch (exception: ClientRequestException) { // 4xx errors
    val errorBody = exception.response.bodyAsText()
    Err(
        ClientError(
            message = exception.message,
            code = exception.response.status.value,
            errorBody = errorBody,
        ),
    )
} catch (exception: ServerResponseException) { // 5xx errors
    Err(ServerError(message = exception.message, code = exception.response.status.value))
} catch (exception: RedirectResponseException) {
    Err(
        ExceptionApiModel.RedirectError(
            message = exception.message,
            code = exception.response.status.value,
        ),
    )
} catch (exception: Exception) {
    Err(Unknown(exception.message ?: UNKNOWN_ERROR_MESSAGE))
}

internal const val NO_INTERNET_ERROR_MESSAGE = "No Internet"
internal const val TIMEOUT_ERROR_MESSAGE = "Timeout"
internal const val UNKNOWN_ERROR_MESSAGE = "Unknown error"
