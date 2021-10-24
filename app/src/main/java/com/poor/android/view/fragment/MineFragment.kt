package com.poor.android.view.fragment

import com.poor.android.R
import com.poor.android.databinding.FragmentMineBinding
import com.poor.android.logic.vm.MainViewModel
import com.poor.android.global.base.BaseFragment

class MineFragment : BaseFragment<MainViewModel, FragmentMineBinding>() {
    override fun initView() {
    }

    override fun initData() {
    }

    override val layoutId: Int
        get() = R.layout.fragment_mine

    override fun getViewModel() = MainViewModel::class.java
}