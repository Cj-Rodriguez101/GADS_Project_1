package com.cjproductions.gadsproject1.ui.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.cjproductions.gadsproject1.R
import com.cjproductions.gadsproject1.models.LearningLeader
import kotlinx.android.synthetic.main.learning_list_item.view.*

class LearningLeaderAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LearningLeader>() {

        override fun areItemsTheSame(oldItem: LearningLeader, newItem: LearningLeader): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: LearningLeader, newItem: LearningLeader): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return LearningLeaderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.learning_list_item,
                parent,
                false
            )

        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is LearningLeaderViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<LearningLeader>) {
        differ.submitList(list)
    }

    class LearningLeaderViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: LearningLeader) = with(itemView) {
            itemView.top_learner_name.text = item.name
            itemView.top_learner_hours.text = (item.hours.toString()).plus(" learning hours")
            itemView.top_learner_location.text = item.country
        }
    }
}