package com.selfapps.rightcart.model.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.utils.Converter


@Dao
@TypeConverters(Converter::class)
interface AddressesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAddress(vararg addresses: Address)

    @Update
    fun updateAddress(vararg addresses: Address)

    @Delete
    fun deleteAddress(vararg addresses: Address)

    @Query("SELECT * FROM addresses")
    fun loadAllAddresses(): LiveData<List<Address>>

    @Query("SELECT * FROM addresses")
    fun exportAddresses(): List<Address>

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
}