package com.poor.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.poor.android.public.base.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}