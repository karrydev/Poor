package com.poor.android.global.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<ViewModel : BaseViewModel, Binding : ViewDataBinding> : Fragment() {

    private var observerList = ArrayList<LifecycleObserver>()
    lateinit var binding: Binding
    lateinit var viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initVM()
        initView()
        initData()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initVM() {
        getViewModel()?.let {
            viewModel = ViewModelProvider(requireActivity()).get(it)
            addObserve(viewModel)
        }
    }

    abstract fun initView()

    abstract fun initData()

    protected fun addObserve(lifecycleObserver: LifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver)
        observerList.add(lifecycleObserver)
    }

    @get: LayoutRes
    abstract val layoutId: Int

    abstract fun getViewModel(): Class<ViewModel>?

    override fun onDestroy() {
        for (observer in observerList) {
            lifecycle.removeObserver(observer)
        }
        super.onDestroy()
    }
}