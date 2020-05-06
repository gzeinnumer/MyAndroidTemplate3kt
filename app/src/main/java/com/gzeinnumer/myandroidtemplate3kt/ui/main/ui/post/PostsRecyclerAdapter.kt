package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import com.gzeinnumer.myandroidtemplate3kt.databinding.LayoutPostListItemBinding
import com.gzeinnumer.myandroidtemplate3kt.util.MyDiffUtilsCallBack
import java.util.*

class PostsRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var data = mutableListOf<ResponsePost>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PostViewHolder(LayoutPostListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostViewHolder).bind(data[position])
//        holder.binding.title
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun insertData(newList: List<ResponsePost>) {
        val callBack = MyDiffUtilsCallBack(data, newList)
        val diffResult = DiffUtil.calculateDiff(callBack)
        this.data.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    fun updateData(oldList: List<ResponsePost>) {
        val callBack = MyDiffUtilsCallBack(data, oldList)
        val diffResult = DiffUtil.calculateDiff(callBack)
        this.data.clear()
        this.data.addAll(oldList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class PostViewHolder(val binding: LayoutPostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: ResponsePost) {
            binding.title.text = post.title
        }
    }
}

