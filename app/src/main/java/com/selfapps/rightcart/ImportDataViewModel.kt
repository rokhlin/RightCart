package com.selfapps.rightcart

import android.net.Uri
import com.selfapps.rightcart.model.db.AddressesDao

class ImportDataViewModel(private val dao: AddressesDao) : BaseViewModel() {
    fun importToDb(selectedFile: Uri?) {
        val list = CSVUtils.csvToAddresses(selectedFile)
        dao.insertAddress(*list.toTypedArray())
    }


}
