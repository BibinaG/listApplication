package com.example.myapplication.handler



import com.example.myapplication.UiState

import retrofit2.Response

suspend fun <T> Response<T>.handleResponse(doActionOnSuccess: suspend (body: T) -> Unit = {}): UiState<T> {
    val genericErrorMessage = "Error encountered. Please try again later."
    return if (isSuccessful) {
        if (body() != null) {
            doActionOnSuccess.invoke(body()!!)
            UiState.Success(body()!!)
        } else {
            UiState.Error(message())
        }
    } else {
        return try {
            UiState.Error(
                errorBody()?.string() ?: "Error Occured !!", 401
            )
        } catch (e: Exception) {
            UiState.Error(
                e.message ?: "Exception: Due to Server Response", 402
            )

        } catch (e: Exception) {
            UiState.Error(genericErrorMessage)
        }
    }
}

