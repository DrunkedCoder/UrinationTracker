package com.example.urinationtracker

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urinationtracker.databinding.ActivityDayRecordsBinding
import com.example.urinationtracker.data.UrinationViewModel

class DayRecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDayRecordsBinding
    private val viewModel: UrinationViewModel by viewModels()
    private lateinit var adapter: DayRecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val selectedDate = intent.getStringExtra("date") ?: return
        binding.tvSelectedDate.text = selectedDate

        adapter = DayRecordsAdapter()
        binding.rvDayRecords.layoutManager = LinearLayoutManager(this)
        binding.rvDayRecords.adapter = adapter
        binding.rvDayRecords.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        // ✅ Observe LiveData directly and submit list to adapter
        viewModel.getRecordsForDate(selectedDate).observe(this) { entries ->
            adapter.submitList(entries)
        }
    }
}




