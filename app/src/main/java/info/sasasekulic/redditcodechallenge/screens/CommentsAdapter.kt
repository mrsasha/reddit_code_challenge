package info.sasasekulic.redditcodechallenge.screens

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.view.ViewGroup
import info.sasasekulic.redditcodechallenge.R
import info.sasasekulic.redditcodechallenge.api.models.*
import info.sasasekulic.redditcodechallenge.inflate
import kotlinx.android.synthetic.main.comment_item.view.*
import kotlinx.android.synthetic.main.comment_rv.view.*
import java.text.SimpleDateFormat
import java.util.*

class CommentsAdapter(val comments: List<Child>) : RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when {
            comments[position] is CommentChildHierarchical -> COMMENT_LIST
            comments[position] is MoreChild -> MORE
            else -> COMMENT_SIMPLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            COMMENT_LIST -> return ViewHolder(parent.inflate(R.layout.comment_rv))
            MORE -> ViewHolder(parent.inflate(R.layout.comment_more))
            else -> ViewHolder(parent.inflate(R.layout.comment_item))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return when {
            getItemViewType(position) == COMMENT_LIST -> holder.bind(comments[position] as CommentChildHierarchical)
            getItemViewType(position) == MORE -> holder.bind(comments[position] as MoreChild)
            else -> holder.bind(comments[position] as CommentChild)
        }
    }

    override fun getItemCount(): Int = comments.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(comment: CommentChild) {
            itemView.comment_body.text = comment.data.body
            itemView.comment_vote_number.text = comment.data.score.toString()
            itemView.comment_author.text = comment.data.author

            try {
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
                val netDate = Date(comment.data.created_utc * 1000)
                itemView.comment_date.text = sdf.format(netDate)
            } catch (e: Exception) {
                Log.e("CommentsAdapter", "error", e)
                itemView.comment_date.text = comment.data.created_utc.toString()
            }
        }

        fun bind(comment: CommentChildHierarchical) {
            itemView.comments_rv.adapter = CommentsAdapter((comment.data.replies as ListingChild).data.children)
        }

        fun bind(comment: MoreChild) {
            //do nothing
        }
    }

    companion object {
        private val COMMENT_SIMPLE = 0

        private val COMMENT_LIST = 1

        private val MORE = 2
    }
}