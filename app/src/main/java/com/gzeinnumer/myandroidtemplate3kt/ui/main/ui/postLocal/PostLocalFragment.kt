package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.gzeinnumer.myandroidtemplate3kt.base.BaseFragment
import com.gzeinnumer.myandroidtemplate3kt.base.BaseResource
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelProviderFactory
import com.gzeinnumer.myandroidtemplate3kt.databinding.FragmentPostLocalBinding
import com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post.PostsRecyclerAdapter
import com.gzeinnumer.myandroidtemplate3kt.util.VerticalSpacingItemDecoration
import com.gzeinnumer.myandroidtemplate3kt.util.myLogD
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class PostLocalFragment : BaseFragment() {

    private val TAG = "PostFragment"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    private lateinit var viewModel: PostLocalVM

    lateinit var binding: FragmentPostLocalBinding

    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val func = "onCreateView+"
        myLogD(TAG, func)

        binding = FragmentPostLocalBinding.inflate(inflater)
        viewModel = ViewModelProviders.of(this, providerFactory)[PostLocalVM::class.java]
        postsRecyclerAdapter = PostsRecyclerAdapter()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val func = "onCreateView+"
        myLogD(TAG, func)

        initSwipeRefresh()
        initRecyclerView()

        subscribeObservers()

    }

    private fun initSwipeRefresh() {
        val func = "initSwipeRefresh+"
        myLogD(TAG, func)
        binding.swipeToRefresh.setOnRefreshListener { subscribeObservers() }
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
        viewModel.getList().observe(viewLifecycleOwner, Observer { listBaseResource ->
            when (listBaseResource.status) {
                BaseResource.BaseResourceStatus.STATUS_1_SUCCESS -> {
                    onSwipeToRefresh(false)
                    listBaseResource.message?.let { onShowSucces(it).show() }
                    listBaseResource.data?.let { postsRecyclerAdapter.insertData(it) }
                }
                BaseResource.BaseResourceStatus.STATUS_2_ERROR -> {
                    onSwipeToRefresh(false)
                    listBaseResource.message?.let { onShowError(it).show() }
                }
                BaseResource.BaseResourceStatus.STATUS_6_LOADING -> onSwipeToRefresh(true)
            }
        })
    }


    private fun onSwipeToRefresh(status: Boolean) {
        binding.swipeToRefresh.isRefreshing = status
    }

}
