package com.poor.android.public.top

import android.widget.Toast
import com.poor.android.public.base.PoorApplication

fun String.showToast (duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(PoorApplication.context, this, duration).show()
}

fun Int.showToast (duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(PoorApplication.context, this, duration).show()
}

