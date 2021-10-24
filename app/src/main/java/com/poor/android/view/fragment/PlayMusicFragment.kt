package com.poor.android.view.fragment

import com.poor.android.R
import com.poor.android.databinding.PlayMusicFragmentBinding
import com.poor.android.logic.vm.PlayMusicViewModel
import com.poor.android.global.base.BaseFragment

class PlayMusicFragment : BaseFragment<PlayMusicViewModel, PlayMusicFragmentBinding>() {

    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun initData() {
        TODO("Not yet implemented")
    }

    override val layoutId: Int
        get() = R.layout.play_music_fragment

    override fun getViewModel() = PlayMusicViewModel::class.java
}