package com.selfapps.rightcart.utils

import android.net.Uri
import android.util.Log
import com.selfapps.rightcart.model.Address
import java.io.File


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

                result.add(
                    Address(
                        cart = splited[0].trim(),
                        zip = zip,
                        city = splited[2].trim(),
                        street = splited[3].trim(),
                        notes = splited[4].trim()
                    )
                )
            }
        }
        return result
    }
}