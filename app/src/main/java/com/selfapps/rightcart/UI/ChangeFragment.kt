package com.selfapps.rightcart.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.selfapps.rightcart.R
import com.selfapps.rightcart.model.Address
import com.selfapps.rightcart.utils.Converter
import kotlinx.android.synthetic.main.change_item.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance


@SuppressLint("ValidFragment")
class ChangeFragment(val id: Long) : Fragment(), KodeinAware {
    override val kodein by kodein()
    private val viewModelFactory: CartViewModelFactory by instance()

    private lateinit var viewModel: ChangeAddressVievModel

    companion object {
        fun newInstance(id: Long) = ChangeFragment(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.change_item, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChangeAddressVievModel::class.java)

        val address = viewModel.getAddress(id)

        et_cart.setText(address.cart)
        val zip: String = if (address.zip != null || address.zip != 0) address.zip.toString() else ""
        et_zip.setText(zip)
        aet_street.setText(address.street)
        aet_city.setText(address.city)
        et_notes.setText(address.notes)

        met_city_translit.setText(Converter.stringFromSets(address.cityTranslit, Converter.TRANSLIT_STRING_SEPARATOR))
        met_street_translit.setText(
            Converter.stringFromSets(
                address.streetTranslit,
                Converter.TRANSLIT_STRING_SEPARATOR
            )
        )


        fab.setOnClickListener { view ->
            val newAddress = Address(
                cart = et_cart.text.toString().trim(),
                zip = et_zip.text.toString().trim().toInt(),
                city = aet_city.text.toString().trim(),
                street = aet_street.text.toString().trim(),
                notes = et_notes.text.toString().trim()
            )

            newAddress.cityTranslit = Converter.setFromString(
                met_city_translit.text.toString(),
                Converter.TRANSLIT_STRING_SEPARATOR
            )
            newAddress.streetTranslit = Converter.setFromString(
                met_street_translit.text.toString(),
                Converter.TRANSLIT_STRING_SEPARATOR
            )
            newAddress.id = address.id

            viewModel.addAddress(newAddress)
            Snackbar.make(this@ChangeFragment.view!!, "Item updated.", Snackbar.LENGTH_SHORT).show()
        }
    }
}
