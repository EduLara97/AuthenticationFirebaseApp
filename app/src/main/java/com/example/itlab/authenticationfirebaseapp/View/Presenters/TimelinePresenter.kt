package com.example.itlab.authenticationfirebaseapp.View.Presenters

import android.util.Log
import com.example.itlab.authenticationfirebaseapp.Models.Contact
import com.example.itlab.authenticationfirebaseapp.View.Activities.Timeline
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TimelinePresenter(val view: Timeline) {

    val db = FirebaseDatabase.getInstance()

    fun setInitialReference(uid: String) {
        db.reference.child(uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val contacts = ArrayList<Contact>()
                if (p0.exists()) {
                    p0.children.forEach { dataSnapshot ->
                        run {
                            val name = dataSnapshot.child("name").value as String
                            val number = dataSnapshot.child("number").value as String
                            val email = dataSnapshot.child("email").value as String
                            val contact = Contact(name, number, email, null)
                            contacts.add(contact)
                        }
                    }
                    view.onContactsReady(contacts)
                }
            }

            override fun onCancelled(p0: DatabaseError?) {
                Log.e("Test:", p0.toString())
            }
        })
    }
}

interface TimelineDelegate {
    fun onContactsReady(contacts: List<Contact>)
}