package com.selfapps.rightcart.UI.importExport

import android.content.Context
import android.net.Uri
import android.os.Environment
import android.util.Log
import com.selfapps.rightcart.UI.BaseViewModel
import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.model.db.AddressesDao
import com.selfapps.rightcart.utils.CSVUtils
import com.selfapps.rightcart.utils.Converter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

class ImportDataViewModel(private val dao: AddressesDao) : BaseViewModel() {
    val builder = StringBuilder("cart;zip;city;street;notes;city_translit;street_translit")

    fun importToDb(selectedFile: Uri?) {
        val list = CSVUtils.csvToAddresses(selectedFile)
        dao.insertAddress(*list.toTypedArray())
    }

    fun exportFromDb(context: Context) = uiScope.launch {


        val filename = "Export_${android.text.format.DateFormat.format("yyyy-MM-dd-hh_mm_ss", java.util.Date())}"

        var file: File? = null

        if (Environment.getExternalStorageState() == null) {
            val directory = File("${Environment.getDataDirectory()}/downloads/")
            if (!directory.exists()) {
                directory.mkdir()
            }

            file = File(directory, filename)
        } else if (Environment.getExternalStorageState() != null) {
            file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), filename)
        }

        getDbString(context)
        CSVUtils.writeFileOnInternalStorage(context, file!!, builder.toString())
    }


    private suspend fun getDbString(context: Context) = withContext(bgDispatcher) {
        val list = dao.exportAddresses()

        list.forEach { address ->
            Log.d("tag", "address = ${address.streetTranslit}.")
            builder.append("\n")
            builder.append(address.cart)
            builder.append(";")
            extractZip(address)
            builder.append(";")
            builder.append(address.city)
            builder.append(";")
            builder.append(address.street)
            builder.append(";")
            builder.append(address.notes)
            builder.append(";")
            expractSets(address.cityTranslit as HashSet<String>)
            builder.append(";")
            expractSets(address.streetTranslit as HashSet<String>)
            builder.append("\n")
        }
    }

    private fun expractSets(set: HashSet<String>) {
        if (set.isEmpty()) builder.append("")
        if (set.size == 1 && set.contains("")) builder.append("")
        else {
            val converter = Converter()
            builder.append(converter.fromStringSet(set))
        }
    }

    private fun extractZip(address: Address) {
        if (address.zip == 0) builder.append("")
        else builder.append(address.zip)
    }
}
