package com.poor.android.global.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import android.os.Build

import android.view.WindowManager

import android.view.Window

import android.app.Activity
import com.poor.android.R
import android.graphics.Color

import android.view.View

abstract class BaseActivity<ViewModel : BaseViewModel, Binding : ViewDataBinding> :
    AppCompatActivity() {

    private val activities = ArrayList<AppCompatActivity>()
    private var observerList = ArrayList<LifecycleObserver>()
    protected lateinit var binding: Binding
    protected lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarColor()
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

    /**
     * 修改状态栏颜色
     */
    private fun setStatusBarColor() {
        val window = window
        window.clearFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
        )
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    abstract fun initView()

    abstract fun initData()

    abstract fun initListener()

    protected fun addObserve(lifecycleObserver: LifecycleObserver) {
        lifecycle.addObserver(lifecycleObserver)
        observerList.add(lifecycleObserver)
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