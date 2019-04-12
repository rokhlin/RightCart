package com.selfapps.rightcart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.main_view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


class ListFragment : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: CartViewModelFactory by instance()

    companion object {
        fun newInstance() = ListFragment()
    }

    private lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)


        val adapter = AddressAdapter(activity!!.applicationContext)
        list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        list.adapter = adapter

        viewModel.loadAddresses().observe(this@ListFragment, Observer {
            adapter.setData(it)
        })

    }

}
