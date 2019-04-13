package com.selfapps.rightcart.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.model.Converter


@Dao
@TypeConverters(Converter::class)
interface AddressesDao {

//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertCityTranslit(vararg cities: CityTranslit)
//
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insertStreetTranslit(vararg streets: StreetTranslit)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAddress(vararg addresses: Address)

//    @Update
//    fun updateCityTranslit(vararg cities: CityTranslit)
//
//    @Update
//    fun updateStreetTranslit(vararg streets: StreetTranslit)

    @Update
    fun updateAddress(vararg addresses: Address)

//    @Delete
//    fun deleteCityTranslit(vararg cities: CityTranslit)
//
//    @Delete
//    fun deleteStreetTranslit(vararg streets: StreetTranslit)

    @Delete
    fun deleteAddress(vararg addresses: Address)

    @Query("SELECT * FROM addresses")
    fun loadAllAddresses(): LiveData<List<Address>>

//    @Query("SELECT * FROM cities")
//    fun loadAllCities(): Array<CityTranslit>
//
//    @Query("SELECT * FROM streets")
//    fun loadAllStreets(): Array<StreetTranslit>

    //COLLATE NOCASE
    @Query("SELECT * FROM addresses WHERE city LIKE :search " +
            "OR city_translit LIKE :search "+
            "OR street_translit LIKE :search " +
            "OR street LIKE :search"
    )
    fun findAddress(search: String): LiveData<List<Address>>

    @Query(
        "SELECT * FROM addresses WHERE city LIKE :search " +
                "OR street LIKE :search"
    )
    fun findByCityStreet(search: String): List<Address>

//    @Query("SELECT * FROM cities WHERE key_id LIKE :search" )
//    fun findCities(search: String): LiveData<List<CityTranslit>>
//
//    @Query("SELECT * FROM streets WHERE key_id LIKE :search" )
//    fun findStreets(search: String): LiveData<List<StreetTranslit>>
}