package com.poor.android.view.activity

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.poor.android.R
import com.poor.android.databinding.ActivityMainBinding
import com.poor.android.global.base.BaseActivity
import com.poor.android.logic.vm.MainViewModel
import com.poor.android.view.fragment.FindFragment
import com.poor.android.view.fragment.MineFragment
import com.poor.android.view.fragment.MoreFragment

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    private val findFragment = FindFragment()
    private val mineFragment = MineFragment()
    private val moreFragment = MoreFragment()
    private val fragments = ArrayList<Fragment>()

    init {
        fragments.apply {
            add(findFragment)
            add(mineFragment)
            add(moreFragment)
        }
    }

    override fun initView() {
        val navController = findNavController(R.id.nav_fragment)
        binding.bottomBar.setupWithNavController(navController)
    }

    override fun initData() {}

    override fun initListener() {
    }

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getViewModel() = MainViewModel::class.java
}