package com.poor.android.view

import com.poor.android.R
import com.poor.android.databinding.ActivityMainBinding
import com.poor.android.public.base.BaseActivity
import com.poor.android.logic.vm.MainViewModel

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getViewModel() = MainViewModel::class.java

    override fun observe() {
        TODO("Not yet implemented")
    }

}