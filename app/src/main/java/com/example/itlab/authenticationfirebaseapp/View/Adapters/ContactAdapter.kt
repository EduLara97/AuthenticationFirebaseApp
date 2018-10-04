package com.example.itlab.authenticationfirebaseapp.View.Adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.itlab.authenticationfirebaseapp.Models.Contact
import com.example.itlab.authenticationfirebaseapp.R
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.contact_item.view.*

class ContactAdapter(private val contacts: List<Contact>, val context: Context) : RecyclerView.Adapter<ContactAdapter.Holder>() {

    private val storage = FirebaseStorage.getInstance().reference

    override fun onCreateViewHolder(p0: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(context).inflate(R.layout.contact_item, p0, false))
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(p0: Holder, position: Int) {
        val contact = contacts[position]
        p0.tvName.text = contact.name
        p0.tvNumber.text = contact.number
        if (contact.image != null) {
            Glide.with(p0.itemView)
                    .load(storage.child(contact.image!!))
                    .into(p0.ivContact)
        }
    }

    class Holder(view: android.view.View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tvName!!
        val tvNumber = view.tvNumber!!
        var ivContact = view.ivContact!!
    }
}