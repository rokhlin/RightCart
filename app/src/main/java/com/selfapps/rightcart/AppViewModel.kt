package com.selfapps.rightcart

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.model.db.AddressesDao

class AppViewModel(private val dao: AddressesDao): ViewModel() {
    fun loadAddresses(): LiveData<List<Address>> {
        return dao.loadAllAddresses()
    }
    //Add methods here
}