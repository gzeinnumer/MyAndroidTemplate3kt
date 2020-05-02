package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gzeinnumer.myandroidtemplate3kt.base.BaseFragment
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelProviderFactory
import com.gzeinnumer.myandroidtemplate3kt.databinding.FragmentPostBinding
import com.gzeinnumer.myandroidtemplate3kt.util.VerticalSpacingItemDecoration
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

class PostFragment : BaseFragment() {

    private val TAG = "PostFragment"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: PostVM

    lateinit var binding: FragmentPostBinding

    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val func = "onCreateView+"
        myLogD(TAG,func)

        binding = FragmentPostBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this, providerFactory)[PostVM::class.java]
        postsRecyclerAdapter = PostsRecyclerAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val func = "onViewCreated+"
        myLogD(TAG, func)

        initRecyclerView()
        subscribeObservers()
    }

    private fun initRecyclerView() {
        val func = "initRecyclerView+"
        myLogD(TAG, func)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpacingItemDecoration(15)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = postsRecyclerAdapter
    }

    private fun subscribeObservers() {
        val func = "subscribeObservers+"
        myLogD(TAG, func)

        viewModel.observePosts()?.removeObservers(viewLifecycleOwner)
        viewModel.observePosts()?.observe(viewLifecycleOwner,
            Observer {
                if(it != null){
                    myLogD(TAG, func+it.data)
                    when(it.status){
                        BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                            myLogD(TAG, func + "STATUS_1_SUCCESS")
                            onHideLoading()
                            onShowSucces(it.message)
                            it.data?.let { it1 -> postsRecyclerAdapter.setPosts(it1) }
                        }
                        BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                            myLogD(TAG, func + "STATUS_2_ERROR")
                            onHideLoading()
                            onShowError(it.message)
                        }
                        BaseResource.BaseResourceStatus.STATUS_6_LOADING -> {
                            myLogD(TAG, func + "STATUS_6_LOADING")
                            onShowLoading()
                        }
                    }
                }
            }
        )
    }
}
