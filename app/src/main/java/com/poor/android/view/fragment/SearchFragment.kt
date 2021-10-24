package com.poor.android.view.fragment

import android.text.Editable
import android.text.TextWatcher
import androidx.core.widget.addTextChangedListener
import com.poor.android.R
import com.poor.android.databinding.FragmentSearchBinding
import com.poor.android.global.base.BaseFragment
import com.poor.android.global.util.L
import com.poor.android.logic.vm.MainViewModel

class SearchFragment : BaseFragment<MainViewModel, FragmentSearchBinding>() {

    override fun initView() {
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                viewModel.searchSongs(p0.toString()).observe(this@SearchFragment) {
                    if (it.isSuccess) {
                        L.e("result==>", it.getOrNull().toString())
                    } else {
                        L.e("faile==>", it.exceptionOrNull().toString())
                    }
                }
            }

        })
    }

    override fun initData() {
    }

    override val layoutId = R.layout.fragment_search

    override fun getViewModel() = MainViewModel::class.java
}