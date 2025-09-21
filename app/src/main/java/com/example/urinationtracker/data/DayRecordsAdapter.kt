package com.example.urinationtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.urinationtracker.data.UrinationEntry
import com.example.urinationtracker.databinding.ItemDayRecordBinding

class DayRecordsAdapter :
    ListAdapter<UrinationEntry, DayRecordsAdapter.DayRecordViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayRecordViewHolder {
        val binding = ItemDayRecordBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DayRecordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DayRecordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DayRecordViewHolder(private val binding: ItemDayRecordBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entry: UrinationEntry) {
            binding.tvTime.text = entry.time
            binding.tvAmount.text = "${entry.amountMl} mL"
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<UrinationEntry>() {
        override fun areItemsTheSame(oldItem: UrinationEntry, newItem: UrinationEntry) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UrinationEntry, newItem: UrinationEntry) =
            oldItem == newItem
    }
}

