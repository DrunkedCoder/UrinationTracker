package com.example.urinationtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.urinationtracker.data.DailyTotal
import com.example.urinationtracker.databinding.ItemDailyTotalBinding

class PastRecordsAdapter(
    private val onItemClick: (String) -> Unit
) : ListAdapter<DailyTotal, PastRecordsAdapter.DailyTotalViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyTotalViewHolder {
        val binding = ItemDailyTotalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DailyTotalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DailyTotalViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class DailyTotalViewHolder(private val binding: ItemDailyTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(dailyTotal: DailyTotal) {
            binding.tvDate.text = dailyTotal.date
            binding.tvTotal.text = "${dailyTotal.totalAmount} mL"

            binding.root.setOnClickListener {
                onItemClick(dailyTotal.date)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DailyTotal>() {
        override fun areItemsTheSame(oldItem: DailyTotal, newItem: DailyTotal) =
            oldItem.date == newItem.date

        override fun areContentsTheSame(oldItem: DailyTotal, newItem: DailyTotal) =
            oldItem == newItem
    }
}
