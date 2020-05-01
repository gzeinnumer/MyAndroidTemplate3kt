package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerFragment

class ProfileFragment : DaggerFragment() {

    private val TAG = "ProfileFragment"

    private lateinit var profileVM: ProfileVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val func = "onCreateView+"
        myLogD(TAG,func)

        profileVM =
            ViewModelProviders.of(this).get(ProfileVM::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        profileVM.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
