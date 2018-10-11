package com.example.itlab.authenticationfirebaseapp.views.presenters

import com.example.itlab.authenticationfirebaseapp.views.activities.RegisterActivity

class RegisterPresenter(val view: RegisterActivity) {

    // Declarar instancia de FirebaseAuth

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

    fun register(email: String, password: String, name: String){
        // Verificar el registro del usuario

    }

    interface RegisterDelegate{
        fun correctRegister()
        fun incorrectRegister(mensaje:String)
    }

}