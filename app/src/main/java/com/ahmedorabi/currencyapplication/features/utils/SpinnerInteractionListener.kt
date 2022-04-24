package com.ahmedorabi.currencyapplication.features.utils

import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView


class SpinnerInteractionListener : AdapterView.OnItemSelectedListener,
    View.OnTouchListener {
    var userSelect = false

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        if (userSelect) {
            // Your selection handling code here
            userSelect = false;
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        userSelect = true;
        return false;
    }


}