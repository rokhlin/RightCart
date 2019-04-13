package com.selfapps.rightcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.selfapps.rightcart.model.db.AddressesDao

class CartViewModelFactory(private val dao: AddressesDao): ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass.canonicalName) {
            AppViewModel::class.java.canonicalName -> AppViewModel(dao) as T
            ImportDataViewModel::class.java.canonicalName -> ImportDataViewModel(dao) as T //TODO: ADD GROUPS REPOSITORY

            else -> throw ClassCastException("wrong ViewModel class")
        }
    }
}