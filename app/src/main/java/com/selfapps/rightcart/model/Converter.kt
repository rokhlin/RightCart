package com.selfapps.rightcart.model

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.io.StringReader
import java.io.StringWriter


class Converter {

//    @TypeConverter
//    fun setToObj(objSet: Set<String>):String{
//       // if(objSet == null) return ""
//
//        return Gson().toJson(objSet)
//    }
//
//    @TypeConverter
//    fun setFromObj(string: String):Set<String> {
//        val founderSetType = object : TypeToken<Set<String>>() {
//        }.getType()
//        return Gson().fromJson(string,founderSetType)
//    }

    private val TAG: String = "TYPE_CONVERTER"

    @TypeConverter
    fun fromStringSet(strings: Set<String>?): String? {
        if (strings == null) {
            return null
        }

        val result = StringWriter()
        val json = JsonWriter(result)

        try {
            json.beginArray()

            for (s in strings) {
                json.value(s)
            }

            json.endArray()
            json.close()
        } catch (e: IOException) {
            Log.e(TAG, "Exception creating JSON", e)
        }

        return result.toString()
    }

    @TypeConverter
    fun toStringSet(strings: String?): Set<String>? {
        if (strings == null) {
            return null
        }

        val reader = StringReader(strings)
        val json = JsonReader(reader)
        val result = HashSet<String>()

        try {
            json.beginArray()

            while (json.hasNext()) {
                result.add(json.nextString())
            }

            json.endArray()
        } catch (e: IOException) {
            Log.e(TAG, "Exception parsing JSON", e)
        }

        return result
    }
}