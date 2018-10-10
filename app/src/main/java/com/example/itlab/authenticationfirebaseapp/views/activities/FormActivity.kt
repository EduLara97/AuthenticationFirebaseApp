package com.example.itlab.authenticationfirebaseapp.views.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.support.design.widget.TextInputLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.views.presenters.FormDelegate
import com.example.itlab.authenticationfirebaseapp.views.presenters.FormPresenter
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity(), FormDelegate {

    private val CAMERA_REQUEST_CODE = 101
    private val mPresenter = FormPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)
        setSupportActionBar(tbTimeline)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageView.setOnClickListener {
            val permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)

            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                Log.i(this.localClassName, "Permission to record denied")
                makeRequest()
            }
            openCamera()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(imageBitmap)
        }
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE)
            }
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.check -> {
                if (checkForCompletion()) {
                    if (imageView.drawable !is ColorDrawable) {
                        val bitmapDrawable = imageView.drawable as BitmapDrawable
                        mPresenter.uploadImage(bitmapDrawable.bitmap)
                    } else {
                        mPresenter.uploadImage(null)
                    }
                }
            }
            android.R.id.home -> {
                onBackPressed()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContactSaved() {
        onBackPressed()
    }

    override fun onImageUploaded(imageUID: String) {
        val values = mPresenter.toHashMap(txtNameInput.text.toString(), txtEmailInput.text.toString(), txtTelephoneInput.text.toString(), imageUID)
        mPresenter.saveContact(values)
    }

    private fun checkForCompletion(): Boolean {
        var complete = true
        if (txtNameInput.text.toString() == "") {
            setError(nameInputLayout)
            complete = false
        }
        if (txtEmailInput.text.toString() == "") {
            setError(emailInputLayout)
            complete = false
        }
        if (txtTelephoneInput.text.toString() == "") {
            setError(telephoneInputLayout)
            complete = false
        }

        return complete
    }

    private fun setError(layout: TextInputLayout) {
        layout.error = getString(R.string.obligatoryError)
    }
}