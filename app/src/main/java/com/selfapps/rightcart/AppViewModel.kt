package com.selfapps.rightcart

import com.selfapps.rightcart.model.db.AddressesDao

class AppViewModel(private val dao: AddressesDao) : BaseViewModel() {

    fun loadAddresses() = dao.loadAllAddresses()

    fun findAdresses(searchQuery: String) = dao.findAddress("%$searchQuery%")

}