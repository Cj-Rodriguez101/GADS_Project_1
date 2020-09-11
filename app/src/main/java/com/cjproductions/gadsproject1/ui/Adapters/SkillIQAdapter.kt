package com.cjproductions.gadsproject1.ui.Adapters

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.cjproductions.gadsproject1.R
import com.cjproductions.gadsproject1.models.SkilledIndividual
import kotlinx.android.synthetic.main.skill_list_item.view.*

class SkillIQAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<SkilledIndividual>() {

        override fun areItemsTheSame(
            oldItem: SkilledIndividual,
            newItem: SkilledIndividual
        ): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: SkilledIndividual,
            newItem: SkilledIndividual
        ): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return SkilledIndividualViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.skill_list_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SkilledIndividualViewHolder -> {
                holder.bind(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<SkilledIndividual>) {
        differ.submitList(list)
    }

    class SkilledIndividualViewHolder
    constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: SkilledIndividual) = with(itemView) {
            itemView.skill_iq_name.text = item.name
            itemView.skill_iq_hours.text = item.score.toString().plus(" skill IQ Score")
            itemView.skill_iq_location.text = item.country
        }
    }

}