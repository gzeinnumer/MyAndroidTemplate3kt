package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.data.room.AppDatabase
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import dagger.android.support.DaggerFragment

class PostFragment : DaggerFragment() {

    private val TAG = "PostFragment"

    private lateinit var postVM: PostVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val func = "onCreateView+"
        myLogD(TAG,func)

        postVM =
            ViewModelProviders.of(this).get(PostVM::class.java)
        val root = inflater.inflate(R.layout.fragment_post, container, false)
        val textView: TextView = root.findViewById(R.id.text_gallery)
        postVM.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
