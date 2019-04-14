package com.selfapps.rightcart.model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.selfapps.rightcart.R
import com.selfapps.rightcart.UI.ChangeActivity
import kotlinx.android.synthetic.main.detailed_item.view.*


class AddressAdapter(private val ctx: Context) : RecyclerView.Adapter<AddressAdapter.Holder>() {

    private var addresses: List<Address>? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(ctx).inflate(
                R.layout.detailed_item,
                parent,
                false
            )
        )
    }

    fun setData(newAddresses: List<Address>){
        addresses = newAddresses
        notifyDataSetChanged()
    }

    override fun getItemCount() = addresses?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context.applicationContext, ChangeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.putExtra("id", this.addresses?.get(position)?.id)
            it.context.startActivity(intent)
        }
        holder.cart.text = this.addresses?.get(position)?.cart ?: ""
        val zip = this.addresses?.get(position)?.zip.toString()

        holder.zip.text = if (zip == "0") "" else zip
        holder.city.text = this.addresses?.get(position)?.city ?: ""
        holder.street.text = this.addresses?.get(position)?.street ?: ""
        val translit = this.addresses?.get(position)?.streetTranslit.toString()
        holder.streetTranslit.text = if (translit == "[]") "" else translit
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