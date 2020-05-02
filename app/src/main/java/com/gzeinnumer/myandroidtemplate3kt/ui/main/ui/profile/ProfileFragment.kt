package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelProviderFactory
import com.gzeinnumer.myandroidtemplate3kt.databinding.FragmentProfileBinding
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    private val TAG = "ProfileFragment"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: ProfileVM

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val func = "onCreateView+"
        myLogD(TAG,func)

        binding = FragmentProfileBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this, providerFactory)[ProfileVM::class.java]
        return binding.root
    }
}
