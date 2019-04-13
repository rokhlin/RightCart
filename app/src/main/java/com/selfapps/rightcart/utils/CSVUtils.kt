package com.selfapps.rightcart.utils

import android.content.Context
import android.net.Uri
import android.util.Log
import com.selfapps.rightcart.model.Address
import java.io.File
import java.io.FileWriter


object CSVUtils {
    fun readFileAsLinesUsingReadLines(fileName: String): List<String> = File(fileName).readLines()

    fun csvToAddresses(fileName: Uri?): List<Address> {
        if (fileName == null) return emptyList()

        val list = readFileAsLinesUsingReadLines(fileName.encodedPath)
        val result = mutableListOf<Address>()
        list.forEach {
            if (!it.contains("cart;zip")) {
                Log.d("CSV", "received = $it")
                var splited = it.split(";")
                val zip = if (splited[1] == null || splited[1] == "") 0 else splited[1].trim().toInt()
                val address = Address(
                    cart = splited[0].trim(),
                    zip = zip,
                    city = splited[2].trim(),
                    street = splited[3].trim(),
                    notes = splited[4].trim()
                )
                val converter = Converter()

                try {
                    if (!splited[5].equals("")) {
                        address.cityTranslit = converter.toStringSet(splited[5])!!
                    }
                } catch (e: java.lang.Exception) {
                }

                try {
                    if (!splited[6].equals("")) {
                        address.streetTranslit = converter.toStringSet(splited[6])!!
                    }
                } catch (e: java.lang.Exception) {
                }

                result.add(address)
            }
        }
        return result
    }


    fun writeFileOnInternalStorage(mcoContext: Context, file: File, sBody: String) {
        try {
            val writer = FileWriter(file)
            writer.append(sBody)
            writer.flush()
            writer.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}