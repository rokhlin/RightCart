package com.selfapps.rightcart

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.selfapps.rightcart.model.Address
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_view.*
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext


class MainActivity : AppCompatActivity(),KodeinAware, LifecycleOwner {
    override val kodeinContext: KodeinContext<*> = kcontext(this)
    override val kodein by org.kodein.di.android.kodein()
    private val viewModelFactory: CartViewModelFactory by instance() //getting an instance from kodein

//
//    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
//        when (item.itemId) {
//            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_dashboard -> {
//                message.setText(R.string.title_dashboard)
//                return@OnNavigationItemSelectedListener true
//            }
//            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
//            }
//        }
//        false
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this@MainActivity,viewModelFactory).get(AppViewModel::class.java)
       // navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        val adapter = AddressAdapter(this@MainActivity)
        list.layoutManager = LinearLayoutManager(this@MainActivity)
        list.adapter = adapter

        viewModel.loadAddresses().observe(this@MainActivity, Observer{
            adapter.setData(it)
        })

    }
}
