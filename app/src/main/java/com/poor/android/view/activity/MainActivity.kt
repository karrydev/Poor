package com.poor.android.view.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.jeremyliao.liveeventbus.LiveEventBus
import com.poor.android.R
import com.poor.android.databinding.ActivityMainBinding
import com.poor.android.global.base.BaseActivity
import com.poor.android.global.top.toActivity
import com.poor.android.global.util.L
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
        //设置状态栏文字颜色及图标为深色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        val navController = findNavController(R.id.nav_fragment)
        binding.bottomBar.setupWithNavController(navController)
        viewModel.toPlaySong.observe(this) {
            if (it) {
                toActivity(this, PlayerActivity::class.java) {
                }
            }
        }
    }

    override fun initData() {}

    override fun initListener() {
    }

    override val layoutId = R.layout.activity_main

    override fun getViewModel() = MainViewModel::class.java
}