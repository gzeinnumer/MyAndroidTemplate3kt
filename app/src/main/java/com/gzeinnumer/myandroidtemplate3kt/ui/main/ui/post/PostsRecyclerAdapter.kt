package com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gzeinnumer.myandroidtemplate3kt.R
import com.gzeinnumer.myandroidtemplate3kt.data.model.ResponsePost
import java.util.*

class PostsRecyclerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var posts: List<ResponsePost> = ArrayList<ResponsePost>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_post_list_item, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        (holder as PostViewHolder).bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<ResponsePost>) {
        this.posts = posts
        notifyDataSetChanged()
    }

    inner class PostViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.title)
        fun bind(post: ResponsePost) {
            title.text = post.title
        }

    }
}

