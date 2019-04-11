package com.selfapps.rightcart.model

import androidx.room.*


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