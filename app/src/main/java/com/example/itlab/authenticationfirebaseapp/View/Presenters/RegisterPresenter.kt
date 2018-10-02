package com.example.itlab.authenticationfirebaseapp.View.Presenters

import com.example.itlab.authenticationfirebaseapp.View.Activities.RegisterActivity
import com.google.firebase.database.FirebaseDatabase

class RegisterPresenter(val view: RegisterActivity) {

     val db = FirebaseDatabase.getInstance().reference

    fun createUserDBReference(uid: String, values: HashMap<String, Any>) {
        db.child("users/$uid").setValue(values)
    }

    fun toHashMap(name: String, email: String): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["email"] = email
        hashMap["name"] = name
        return hashMap
    }


}