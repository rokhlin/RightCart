package com.selfapps.rightcart.UI


import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.model.db.AddressesDao

class ChangeAddressVievModel(private val dao: AddressesDao) : BaseViewModel() {

    fun getAddress(id: Long) = dao.findAddress(id)
    fun addAddress(newAddress: Address) {
        dao.updateAddress(newAddress)
    }


}
