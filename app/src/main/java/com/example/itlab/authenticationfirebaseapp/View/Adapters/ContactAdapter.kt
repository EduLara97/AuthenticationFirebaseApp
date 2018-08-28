package com.example.itlab.authenticationfirebaseapp.View.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.itlab.authenticationfirebaseapp.Models.Contact
import com.example.itlab.authenticationfirebaseapp.R
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter(val contacts: List<Contact>, val context: Context) : RecyclerView.Adapter<ContactAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false))
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: Holder?, position: Int) {
        val contact = contacts.get(position)
        holder?.tvName?.text = contact.name
        holder?.tvNumber?.text = contact.number
        holder?.ivContact?.setImageBitmap(contact.image)
    }

    class Holder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvName
        val tvNumber = view.tvNumber
        var ivContact = view.ivContact
    }
}