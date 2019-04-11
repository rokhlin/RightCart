package com.selfapps.rightcart.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.selfapps.rightcart.model.Address

@Database(entities = arrayOf(Address::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dao(): AddressesDao
}