package com.poor.android.global.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity<ViewModel : BaseViewModel, Binding : ViewDataBinding> :
    AppCompatActivity() {

    private val activities = ArrayList<AppCompatActivity>()
    private var observerList = ArrayList<LifecycleObserver>()
    protected lateinit var binding: Binding
    protected lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activities.add(this)
        binding = DataBindingUtil.setContentView(this, layoutId)

        initVM()
        initView()
        initData()
        initListener()
    }

    private fun initVM() {
        getViewModel()?.let {
            viewModel = ViewModelProvider(this).get(it)
            addObserve(viewModel)
        }
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initListener()

    protected fun addObserve(lifecycleObserver: LifecycleObserver? = null, block: () -> Unit = {}) {
        if (lifecycleObserver != null) {
            lifecycle.addObserver(lifecycleObserver)
            observerList.add(lifecycleObserver)
        }
        block()
    }

    protected fun finishAllActivity() {
        activities.forEach { it.finish() }
        activities.clear()
    }

    @get: LayoutRes
    abstract val layoutId: Int

    abstract fun getViewModel(): Class<ViewModel>?

    override fun onDestroy() {
        activities.remove(this)
        for (observer in observerList) {
            lifecycle.removeObserver(observer)
        }
        super.onDestroy()
    }
}