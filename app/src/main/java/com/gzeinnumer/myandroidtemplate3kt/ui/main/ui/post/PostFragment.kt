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

        initSwipeRefresh()
        initRecyclerView()
//        subscribeObserversCall(false)
        subscribeObserversRx1(false)
//        subscribeObserversRx2(false)
    }

    private fun initSwipeRefresh() {
        val func = "initSwipeRefresh+"
        myLogD(TAG, func)

        binding.swipeToRefresh.setOnRefreshListener {
//            subscribeObserversCall(true)
            subscribeObserversRx1(true)
//            subscribeObserversRx2(true)
        }
    }

    private fun initRecyclerView() {
        val func = "initRecyclerView+"
        myLogD(TAG, func)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        val itemDecoration = VerticalSpacingItemDecoration(15)
        binding.recyclerView.addItemDecoration(itemDecoration)
        binding.recyclerView.adapter = postsRecyclerAdapter
    }


    var isFirst = false
    private fun subscribeObserversCall(isLoadNiew: Boolean){
        val func = "subscribeObserversCall+"
        myLogD(TAG, func)

        isFirst = true
        viewModel.observePostsCall(isLoadNiew).observe(viewLifecycleOwner, Observer {
            if(it != null){
                myLogD(TAG, func+it.data)
                when(it.status){
                    BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                        myLogD(TAG, func + "STATUS_1_SUCCESS")
                        onSwipeToRefresh(false)
                        it.message?.let { it1 -> onShowSucces(it1).show() }
                        it.data?.let { it1 -> postsRecyclerAdapter.insertData(it1) }
                    }
                    BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                        myLogD(TAG, func + "STATUS_2_ERROR")
                        onSwipeToRefresh(false)
                        if(isFirst){
                            isFirst = false
                            it.message?.let { it1 -> onShowError(it1).show() }
                        }
                    }
                    BaseResource.BaseResourceStatus.STATUS_6_LOADING -> {
                        myLogD(TAG, func + "STATUS_6_LOADING")
                        onSwipeToRefresh(true)
                    }
                }
            }
        })

    }
    private fun subscribeObserversRx1(isLoadNew: Boolean) {
        val func = "subscribeObserversRx1+"
        myLogD(TAG, func)

        isFirst = true
        viewModel.observePostsRx1(isLoadNew)?.removeObservers(viewLifecycleOwner)
        viewModel.observePostsRx1(isLoadNew)?.observe(viewLifecycleOwner,
            Observer {
                if(it != null){
                    myLogD(TAG, func+it.data)
                    when(it.status){
                        BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                            myLogD(TAG, func + "STATUS_1_SUCCESS")
                            onSwipeToRefresh(false)
                            it.message?.let { it1 -> onShowSucces(it1).show() }
                            it.data?.let { it1 -> postsRecyclerAdapter.insertData(it1) }
                        }
                        BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                            myLogD(TAG, func + "STATUS_2_ERROR")
                            onSwipeToRefresh(false)
                            if(isFirst){
                                isFirst = false
                                it.message?.let { it1 -> onShowError(it1).show() }
                            }
                        }
                        BaseResource.BaseResourceStatus.STATUS_6_LOADING -> {
                            myLogD(TAG, func + "STATUS_6_LOADING")
                            onSwipeToRefresh(true)
                        }
                    }
                }
            }
        )
    }

    private fun subscribeObserversRx2(isLoadNiew: Boolean) {
        val func = "subscribeObserversRx2+"
        myLogD(TAG, func)

        isFirst = true
        viewModel.observePostsRx2(isLoadNiew).observe(viewLifecycleOwner,
            Observer {
                if(it != null){
                    myLogD(TAG, func+it.data)
                    when(it.status){
                        BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                            myLogD(TAG, func + "STATUS_1_SUCCESS")
                            onSwipeToRefresh(false)
                            it.message?.let { it1 -> onShowSucces(it1).show() }
                            it.data?.let { it1 -> postsRecyclerAdapter.insertData(it1) }
                        }
                        BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                            myLogD(TAG, func + "STATUS_2_ERROR")
                            onSwipeToRefresh(false)
                            if(isFirst){
                                isFirst = false
                                it.message?.let { it1 -> onShowError(it1).show() }
                            }
                        }
                        BaseResource.BaseResourceStatus.STATUS_6_LOADING -> {
                            myLogD(TAG, func + "STATUS_6_LOADING")
                            onSwipeToRefresh(true)
                        }
                    }
                }
            }
        )

    }

    private fun onSwipeToRefresh(status: Boolean) {
        binding.swipeToRefresh.isRefreshing = status
    }
}
