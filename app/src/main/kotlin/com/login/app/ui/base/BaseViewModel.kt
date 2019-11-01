package com.login.app.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


open class BaseViewModel : ViewModel() {

    private var viewModelJob = Job()
    protected val viewModelScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun <P> executeInBG(block: suspend CoroutineScope.() -> P) {
        execute(block, viewModelScope, Dispatchers.IO)
    }

    fun <P> executeInMain(block: suspend CoroutineScope.() -> P) {
        execute(block, viewModelScope, Dispatchers.Main)
    }

    private inline fun <P> execute(
        crossinline block: suspend CoroutineScope.() -> P,
        scope: CoroutineScope,
        context: CoroutineContext
    ) {
        scope.launch {
            withContext(context) {
                return@withContext block.invoke(this)
            }
        }
    }
}