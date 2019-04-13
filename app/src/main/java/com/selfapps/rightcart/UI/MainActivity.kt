package com.selfapps.rightcart.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.selfapps.rightcart.R
import com.selfapps.rightcart.UI.importExport.ImportDataFragment
import com.selfapps.rightcart.UI.searchList.ListFragment
import com.selfapps.rightcart.utils.addFragment
import com.selfapps.rightcart.utils.replaceFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext


class MainActivity : AppCompatActivity(),KodeinAware, LifecycleOwner {
    override val kodeinContext: KodeinContext<*> = kcontext(this)
    override val kodein by org.kodein.di.android.kodein()
    private val viewModelFactory: CartViewModelFactory by instance() //getting an instance from kodein


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                replaceFragment(
                    ListFragment.newInstance(),
                    R.id.frameLayout
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //TODO Add behaviour
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                replaceFragment(
                    ImportDataFragment.newInstance(),
                    R.id.frameLayout
                )
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        if (savedInstanceState == null) {
            addFragment(
                ListFragment.newInstance(),
                R.id.frameLayout
            )
        }



    }
}
