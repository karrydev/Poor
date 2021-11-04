package com.poor.android.view.activity

import android.os.Bundle
import com.poor.android.R
import com.poor.android.databinding.ActivityPlayerBinding
import com.poor.android.global.base.BaseActivity
import com.poor.android.logic.vm.PlayerViewModel

class PlayerActivity : BaseActivity<PlayerViewModel, ActivityPlayerBinding>() {
    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
    }

    override val layoutId = R.layout.activity_player

    override fun getViewModel() = PlayerViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        overridePendingTransition(R.anim.action_bottom_in, R.anim.action_silent)
        super.onCreate(savedInstanceState)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.action_silent, R.anim.action_bottom_out)
    }
}