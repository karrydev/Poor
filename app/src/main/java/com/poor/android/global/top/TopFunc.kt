package com.poor.android.global.top

import android.widget.Toast
import com.poor.android.global.base.PoorApplication

fun String.showToast (duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(PoorApplication.context, this, duration).show()
}

fun Int.showToast (duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(PoorApplication.context, this, duration).show()
}

