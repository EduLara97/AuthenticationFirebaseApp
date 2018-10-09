package com.example.itlab.authenticationfirebaseapp.View.Presenters

import android.util.Log
import com.example.itlab.authenticationfirebaseapp.Models.Contact
import com.example.itlab.authenticationfirebaseapp.View.Activities.TimelineActivity

class TimelinePresenter(val view: TimelineActivity) {

    //Firebase Database and Auth Reference

    fun setInitialReference() {
        //Firebase Database Read
    }
}

interface TimelineDelegate {
    fun onContactsReady(contacts: List<Contact>)
}