package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.myandroidtemplate3kt.R
import dagger.android.support.DaggerFragment

class ProfileFragment : DaggerFragment() {

    private lateinit var profileVM: ProfileVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
