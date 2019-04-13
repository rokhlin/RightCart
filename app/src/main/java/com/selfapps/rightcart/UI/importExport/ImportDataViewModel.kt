package com.selfapps.rightcart.UI.importExport

import android.net.Uri
import com.selfapps.rightcart.UI.BaseViewModel
import com.selfapps.rightcart.model.db.AddressesDao
import com.selfapps.rightcart.utils.CSVUtils

class ImportDataViewModel(private val dao: AddressesDao) : BaseViewModel() {
    fun importToDb(selectedFile: Uri?) {
        val list = CSVUtils.csvToAddresses(selectedFile)
        dao.insertAddress(*list.toTypedArray())
    }


}
