package com.example.itlab.authenticationfirebaseapp.views.presenters

import android.graphics.Bitmap
import com.example.itlab.authenticationfirebaseapp.views.activities.FormActivity
import java.util.*

class FormPresenter(val view: FormActivity) {
    //Firebase References and UIDs

    fun saveContact(values: HashMap<String, Any>) {
        //Firebase Write
    }

    fun toHashMap(name: String, email: String, telephone: String, imagePath: String): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        hashMap["email"] = email
        hashMap["name"] = name
        hashMap["number"] = telephone
        hashMap["imagePath"] = imagePath
        return hashMap
    }

    fun uploadImage(image: Bitmap?) {
        if (image == null) {
            view.onImageUploaded("")
        }

        //Firebase Storage Image Upload
    }
}

interface FormDelegate {
    fun onContactSaved()

    fun onImageUploaded(imageUID: String)
}