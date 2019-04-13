package com.selfapps.rightcart

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
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

    private val searchQuery = MutableLiveData<String>()

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





        searchQuery.postValue("")
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AppViewModel::class.java)


        val adapter = AddressAdapter(activity!!.applicationContext)
        list.layoutManager = LinearLayoutManager(activity!!.applicationContext)
        list.adapter = adapter

        aet_search_city.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                val query = aet_search_city.text.toString().trim()


                searchQuery.postValue(query)

//
//
//
//                if(query=="") viewModel.loadAddresses().observe(this@ListFragment,
//                    Observer {
//                        adapter.setData(it) })
//
//                else if(query.length >= 2)
//                    viewModel.findAdresses(query).observe(this@ListFragment,
//                        Observer {
//                            adapter.setData(it) })
            }
        })

        searchQuery.observe(this@ListFragment,
            Observer { query ->
                if (query == "") viewModel.loadAddresses().observe(this@ListFragment,
                    Observer {
                        adapter.setData(it)
                    })
                else if (query.length >= 2)
                    viewModel.findAdresses(query).observe(this@ListFragment,
                        Observer {
                            adapter.setData(it)
                        })
            })

        searchQuery.postValue("")

//        viewModel.loadAddresses()
//
//        viewModel.getAddresses().observe(this@ListFragment, Observer {
//            adapter.setData(it)
//        })


        btn_clear_search.setOnClickListener {
            val query = aet_search_city.text.toString().trim()

            if (query == "") viewModel.loadAddresses()
            else if (query.length >= 2) viewModel.findAdresses(query).observe(this@ListFragment, Observer {
                adapter.setData(it)
            })


            //aet_search_city.text.clear()
            //viewModel.loadAddresses()
        }


    }

}
