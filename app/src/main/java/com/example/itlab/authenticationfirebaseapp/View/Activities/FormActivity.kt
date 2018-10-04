package com.example.itlab.authenticationfirebaseapp.View.Activities

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.itlab.authenticationfirebaseapp.R
import com.example.itlab.authenticationfirebaseapp.View.Presenters.FormDelegate
import com.example.itlab.authenticationfirebaseapp.View.Presenters.FormPresenter
import kotlinx.android.synthetic.main.activity_form.*

class FormActivity : AppCompatActivity(), FormDelegate {

    private val mPresenter = FormPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_form)
        setSupportActionBar(tbTimeline)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        imageView.setOnClickListener { view -> }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_form, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.check -> {
                val bitmapDrawable = imageView.drawable as BitmapDrawable
                mPresenter.uploadImage(bitmapDrawable.bitmap)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onContactSaved() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onImageUploaded(imageUID: String) {
        val values = mPresenter.toHashMap(txtNameInput.text.toString(), txtEmailInput.text.toString(), txtTelephoneInput.text.toString(), imageUID)
        mPresenter.saveContact(values)
    }
}