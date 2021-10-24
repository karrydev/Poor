package com.poor.android.view.fragment

import android.view.View
import androidx.navigation.Navigation
import com.poor.android.R
import com.poor.android.databinding.FragmentFindBinding
import com.poor.android.logic.vm.MainViewModel
import com.poor.android.global.base.BaseFragment

class FindFragment : BaseFragment<MainViewModel, FragmentFindBinding>() {

    override fun initView() {
        binding.clickHandle = ClickHandle()
    }

    override fun initData() {
    }


    class ClickHandle {

        fun onSearchETClick(v: View) {
            Navigation.findNavController(v).navigate(R.id.action_navigation_find_to_searchFragment)
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_find

    override fun getViewModel() = MainViewModel::class.java

}