package com.selfapps.rightcart

import android.app.Application
import androidx.room.Room
import com.selfapps.rightcart.model.db.AddressesDao
import com.selfapps.rightcart.model.db.AppDatabase
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CartApp: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<AppDatabase>() with singleton { Room.databaseBuilder(this@CartApp, AppDatabase::class.java, "addresses-db")
            .allowMainThreadQueries() //TODO wrap request with corutines
            //.addMigrations(MIGRATION_1_2) //if you have migrations
            .build() } //Room Implementation

        bind<AddressesDao>() with singleton { instance<AppDatabase>().dao() }

        bind() from provider {(CartViewModelFactory(instance()))} //Using ViewModel provider we can instantiate ViewModelFactory using itself from Activity
    }
}