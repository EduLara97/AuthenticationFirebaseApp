package com.example.itlab.authenticationfirebaseapp.views.presenters

import android.graphics.Bitmap
import android.util.Log
import com.example.itlab.authenticationfirebaseapp.views.activities.FormActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.util.*

class FormPresenter(val view: FormActivity) {
    private val db = FirebaseDatabase.getInstance().reference
    private val storage = FirebaseStorage.getInstance().reference
    private val uidUser = FirebaseAuth.getInstance().currentUser!!.uid
    private val uidContact = UUID.randomUUID().toString()
    private val path = "users/$uidUser/contacts/$uidContact"


    fun saveContact(values: HashMap<String, Any>) {
        db.child(path).setValue(values)
        view.onContactSaved()
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

        val imageRef = storage.child(path)
        val baos = ByteArrayOutputStream()
        image!!.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        imageRef.putBytes(data).addOnSuccessListener {
            view.onImageUploaded(imageRef.path)
        }.addOnFailureListener { exception ->
            Log.e("Firebase Storage Error", exception.localizedMessage)
        }

    }
}

interface FormDelegate {
    fun onContactSaved()

    fun onImageUploaded(imageUID: String)
}