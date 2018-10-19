package com.example.itlab.authenticationfirebaseapp.views.presenters

import android.util.Log
import com.example.itlab.authenticationfirebaseapp.models.Contact
import com.example.itlab.authenticationfirebaseapp.views.activities.TimelineActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TimelinePresenter(val view: TimelineActivity) {

    private val db = FirebaseDatabase.getInstance().reference
    private val uid = FirebaseAuth.getInstance().currentUser!!.uid

    fun setInitialReference() {
        db.child("users/$uid/contacts").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val contacts = ArrayList<Contact>()
                if (p0.value != null) {
                    p0.children.forEach { dataSnapshot ->
                        run {
                            val name = dataSnapshot.child("name").value as String
                            val number = dataSnapshot.child("number").value as String
                            val email = dataSnapshot.child("email").value as String
                            val imagePath = dataSnapshot.child("imagePath").value as String

                            val contact = Contact(name, number, email, imagePath)
                            contacts.add(contact)
                        }
                    }
                    view.onContactsReady(contacts)
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("Test:", p0.toString())
            }
        })

    }
}

interface TimelineDelegate {
    fun onContactsReady(contacts: List<Contact>)
}