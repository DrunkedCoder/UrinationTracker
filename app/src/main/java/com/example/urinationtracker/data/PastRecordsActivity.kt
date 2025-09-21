package com.example.urinationtracker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.urinationtracker.data.UrinationDatabase
import com.example.urinationtracker.data.UrinationRepository
import com.example.urinationtracker.data.UrinationViewModel
import com.example.urinationtracker.data.UrinationViewModelFactory
import com.example.urinationtracker.databinding.ActivityPastRecordsBinding

class PastRecordsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPastRecordsBinding
    private lateinit var viewModel: UrinationViewModel
    private lateinit var adapter: PastRecordsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPastRecordsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Initialize ViewModel with repository
        val dao = UrinationDatabase.getDatabase(application).urinationDao()
        val repository = UrinationRepository(dao)
        val factory = UrinationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UrinationViewModel::class.java]

        // ✅ Setup RecyclerView
        adapter = PastRecordsAdapter { selectedDate ->
            val intent = Intent(this, DayRecordsActivity::class.java)
            intent.putExtra("date", selectedDate)
            startActivity(intent)
        }
        binding.rvPastRecords.layoutManager = LinearLayoutManager(this)
        binding.rvPastRecords.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        binding.rvPastRecords.adapter = adapter

        // ✅ Observe daily totals
        viewModel.dailyTotals.observe(this) { totals ->
            adapter.submitList(totals)
        }
    }
}




