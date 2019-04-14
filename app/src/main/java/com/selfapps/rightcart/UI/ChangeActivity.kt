package com.selfapps.rightcart.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import com.selfapps.rightcart.R
import com.selfapps.rightcart.utils.addFragment
import kotlinx.android.synthetic.main.activity_change.*
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext

class ChangeActivity : AppCompatActivity(), KodeinAware, LifecycleOwner {
    override val kodeinContext: KodeinContext<*> = kcontext(this)
    override val kodein by org.kodein.di.android.kodein()
    private val viewModelFactory: CartViewModelFactory by instance() //getting an instance from kodein

    var id: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change)
        setSupportActionBar(toolbar)

        id = intent.extras.getLong("id")


        if (savedInstanceState == null) {
            addFragment(
                ChangeFragment.newInstance(id),
                R.id.fragcontainer
            )
        }


    }

}
