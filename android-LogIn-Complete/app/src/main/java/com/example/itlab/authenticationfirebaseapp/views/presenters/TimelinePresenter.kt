package com.example.itlab.authenticationfirebaseapp.views.presenters

import com.example.itlab.authenticationfirebaseapp.models.Contact
import com.example.itlab.authenticationfirebaseapp.views.activities.TimelineActivity

class TimelinePresenter(val view: TimelineActivity) {

    //Firebase Database and Auth Reference

    fun setInitialReference() {
        //Firebase Database Read
    }
}

interface TimelineDelegate {
    fun onContactsReady(contacts: List<Contact>)
}