package com.selfapps.rightcart.UI

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class BaseViewModel: ViewModel(){
    private val job = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)
    protected val bgDispatcher: CoroutineDispatcher = Dispatchers.IO


    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }
}