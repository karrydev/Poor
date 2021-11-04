package com.poor.android.global.top

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.poor.android.global.base.PoorApplication

fun String.showToast (duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(PoorApplication.context, this, duration).show()
}

fun Int.showToast (duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(PoorApplication.context, this, duration).show()
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    if (currentFocus == null) View(this) else currentFocus?.let { hideKeyboard(it) }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

private val inputManager = PoorApplication.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
var View.isSoftInputShow: Boolean
    get() = false
    set(value) = if (value) {
        inputManager.showSoftInput(this, 0)
        Unit
    } else {
        inputManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        Unit
    }

inline fun FragmentManager.inTransaction(block: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().block().commit()
}

fun <Clazz> FragmentActivity.toActivity(context: Context, clazz: Class<Clazz>, block: (Intent.() -> Unit)? = null) {
    val intent = Intent(context, clazz)
    block?.let { intent.apply(it) }
    startActivity(intent)
}

fun <Clazz> Fragment.toActivity(context: Context, clazz: Class<Clazz>, block: (Intent.() -> Unit)? = null) {
    val intent = Intent(context, clazz)
    block?.let { intent.apply(it) }
    startActivity(intent)
}