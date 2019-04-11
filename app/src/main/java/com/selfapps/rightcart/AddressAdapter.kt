package com.selfapps.rightcart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selfapps.rightcart.model.Address
import kotlinx.android.synthetic.main.detailed_item.view.*

class AddressAdapter(val ctx: Context): RecyclerView.Adapter<AddressAdapter.Holder>() {

    private var addresses: List<Address>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder{
       return Holder(LayoutInflater.from(ctx).inflate(R.layout.detailed_item, parent, false))
    }

    fun setData(newAddresses: List<Address>){
        addresses = newAddresses
    }

    override fun getItemCount() = addresses?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.cart.text = this.addresses?.get(position)?.cart ?: ""
        holder.zip.text = this.addresses?.get(position)?.zip.toString()
        holder.city.text = this.addresses?.get(position)?.city ?: ""
        holder.street.text = this.addresses?.get(position)?.street ?: ""
        holder.streetTranslit.text = this.addresses?.get(position)?.streetTranslit.toString()
        holder.id.text = this.addresses?.get(position)?.id.toString()
    }


    class Holder(val view: View): RecyclerView.ViewHolder(view) {
            val id = view.tv_id
            val zip = view.tv_zip
            val cart = view.tv_cart
            val city = view.tv_city
            val street = view.tv_street
            val streetTranslit = view.tv_translit
    }
}