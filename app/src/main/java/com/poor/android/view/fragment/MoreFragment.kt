package com.poor.android.view.fragment

import com.poor.android.R
import com.poor.android.databinding.FragmentMoreBinding
import com.poor.android.logic.vm.MainViewModel
import com.poor.android.global.base.BaseFragment

class MoreFragment : BaseFragment<MainViewModel, FragmentMoreBinding>() {
    override fun initView() {
    }

    override fun initData() {
    }

    override val layoutId: Int
        get() = R.layout.fragment_more

    override fun getViewModel() = MainViewModel::class.java
}