package com.example.itlab.authenticationfirebaseapp.View.Presenters

import com.example.itlab.authenticationfirebaseapp.View.Activities.RegisterActivity

class RegisterPresenter(val view: RegisterActivity) {

     //Firebase Database reference

    fun createUserDBReference(uid: String, values: HashMap<String, Any>) {
        //Firebase Write Data
    }

    fun toHashMap(name: String, email: String): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["email"] = email
        hashMap["name"] = name
        return hashMap
    }


}