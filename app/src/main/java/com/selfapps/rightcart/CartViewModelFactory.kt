package com.selfapps.rightcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selfapps.rightcart.model.db.AddressesDao

class CartViewModelFactory(private val dao: AddressesDao): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AppViewModel(dao) as T
    }
}