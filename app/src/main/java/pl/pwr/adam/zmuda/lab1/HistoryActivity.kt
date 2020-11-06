package pl.pwr.adam.zmuda.lab1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.adam.zmuda.lab1.adapters.CalculationAdapter
import pl.pwr.adam.zmuda.lab1.data.Calculation
import pl.pwr.adam.zmuda.lab1.utils.SharedPreferencesUtils
import java.util.*

class HistoryActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewManager = LinearLayoutManager(this)

        val sharedPreference = getSharedPreferences(SharedPreferencesUtils.SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE)
        val history: LinkedList<Calculation> = SharedPreferencesUtils.readHistory(sharedPreference)
        viewAdapter = CalculationAdapter(this, history)

        recyclerView = findViewById<RecyclerView>(R.id.historyRV).apply {
            setHasFixedSize(true)

            layoutManager = viewManager
            adapter = viewAdapter
        }

        findViewById<TextView>(R.id.noRecordsTV).visibility = if (history.count() > 0)  View.GONE else View.VISIBLE
    }
}