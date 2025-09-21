package com.example.urinationtracker

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.urinationtracker.data.DailyTotal
import com.example.urinationtracker.databinding.ItemDailyTotalBinding

class DailyTotalsAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<DailyTotalsAdapter.ViewHolder>() {

    private var items = listOf<DailyTotal>()

    fun submitList(list: List<DailyTotal>) {
        items = list
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemDailyTotalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DailyTotal, onClick: (String) -> Unit) {
            binding.tvDate.text = item.date
            binding.tvTotal.text = "${item.totalAmount} mL" // ✅ Changed to totalAmount
            binding.root.setOnClickListener { onClick(item.date) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemDailyTotalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], onClick)
    }
}
