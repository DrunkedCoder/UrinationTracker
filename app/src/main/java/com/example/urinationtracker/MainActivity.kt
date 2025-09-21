package com.example.urinationtracker

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.urinationtracker.data.*
import com.example.urinationtracker.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UrinationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ ViewModel initialization using Repository + Factory
        val dao = UrinationDatabase.getDatabase(application).urinationDao()
        val repository = UrinationRepository(dao)
        val factory = UrinationViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UrinationViewModel::class.java]

        updateDateTime()

        // ✅ Add entry
        binding.btnAdd.setOnClickListener {
            val amountText = binding.etAmount.text.toString()
            if (amountText.isNotEmpty()) {
                val amountInt = amountText.toIntOrNull()
                if (amountInt != null) {
                    viewModel.addEntry(amountInt)
                    binding.etAmount.text.clear()
                } else {
                    binding.etAmount.error = "Please enter a valid number"
                }
            }
        }

        // ✅ Navigate to Past Records
        binding.btnPastRecords.setOnClickListener {
            startActivity(Intent(this, PastRecordsActivity::class.java))
        }

        // ✅ Observe today's entries
        viewModel.todayEntries.observe(this) { entries ->
            val totalToday = entries.sumOf { it.amountMl }
            binding.tvTotalToday.text = "Total today: $totalToday mL"

            val entriesChart = entries.mapIndexed { index, entry ->
                Entry(index.toFloat(), entry.amountMl.toFloat())
            }
            val dataSet = LineDataSet(entriesChart, "Urination (mL)")

            val nightModeFlags = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            val chartTextColor = if (nightModeFlags == Configuration.UI_MODE_NIGHT_YES) {
                ContextCompat.getColor(this, R.color.chartTextColorLight)
            } else {
                ContextCompat.getColor(this, R.color.chartTextColorDark)
            }

            binding.chartToday.xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                textColor = chartTextColor
                textSize = 12f
            }
            binding.chartToday.axisLeft.apply {
                textColor = chartTextColor
                textSize = 12f
            }
            binding.chartToday.axisRight.apply {
                textColor = chartTextColor
                textSize = 12f
            }
            binding.chartToday.legend.apply {
                textColor = chartTextColor
                textSize = 12f
            }
            binding.chartToday.description.apply {
                textColor = chartTextColor
                textSize = 12f
            }

            dataSet.valueTextColor = chartTextColor
            dataSet.valueTextSize = 12f
            dataSet.setDrawCircles(true)
            dataSet.circleRadius = 5f
            dataSet.setCircleColor(chartTextColor)

            binding.chartToday.data = LineData(dataSet)
            binding.chartToday.invalidate()

            binding.tvTotalToday.setTextColor(chartTextColor)
        }
    }

    private fun updateDateTime() {
        val now = Date()
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        binding.tvDateTime.text = formatter.format(now)
    }
}





