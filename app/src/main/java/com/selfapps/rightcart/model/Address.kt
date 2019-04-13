package com.selfapps.rightcart.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.selfapps.rightcart.utils.Converter


@Entity(tableName = "addresses")
data class Address(
    val cart: String?,
    val zip: Int?,
    val city: String?,
    val street: String?,
    val notes: String?
) {
    @TypeConverters(Converter::class)
    @ColumnInfo(name = "city_translit")
    var cityTranslit: Set<String> = setOf("")

    @TypeConverters(Converter::class)
    @ColumnInfo(name = "street_translit")
    var streetTranslit: Set<String> = setOf("")


    @PrimaryKey(autoGenerate = true)
    var id:Long = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Address) return false

        if (zip != other.zip) return false
        if (city != other.city) return false
        if (street != other.street) return false

        return true
    }

    override fun hashCode(): Int {
        var result = zip ?: 0
        result = 31 * result + (city?.hashCode() ?: 0)
        result = 31 * result + (street?.hashCode() ?: 0)
        return result
    }


}

//, foreignKeys = arrayOf(
//ForeignKey(
//entity = CityTranslit::class,
//parentColumns = arrayOf("key_id"),
//childColumns = arrayOf("city_translit")),
//ForeignKey(
//entity = StreetTranslit::class,
//parentColumns = arrayOf("key_id"),
//childColumns = arrayOf("street_translit"))
//)