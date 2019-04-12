package com.selfapps.rightcart

import androidx.lifecycle.LiveData
import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.model.db.AddressesDao

class AppViewModel(private val dao: AddressesDao) : BaseViewModel() {
    fun loadAddresses(): LiveData<List<Address>> {
        return dao.loadAllAddresses()
    }
    //Add methods here
}