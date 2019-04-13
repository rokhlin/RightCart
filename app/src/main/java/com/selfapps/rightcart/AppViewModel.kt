package com.selfapps.rightcart

import com.selfapps.rightcart.model.db.AddressesDao

class AppViewModel(private val dao: AddressesDao) : BaseViewModel() {
//    var dataset = MutableLiveData<List<Address>>()
//    fun getAddresses() = dataset as LiveData<List<Address>>


    fun loadAddresses() = dao.loadAllAddresses()


    fun findAdresses(s: String) = dao.findAddress("%$s%")

//    fun loadAddresses() = uiScope.launch {
//        val result = withContext(bgDispatcher){dao.loadAllAddresses()}
//        dataset.postValue(result)
//    }
//
//    fun findAdresses(s: String) = uiScope.launch {
//        val result = withContext(bgDispatcher){dao.findAddress(s)}
//        dataset.setValue(result)
//    }
    //Add methods here
}