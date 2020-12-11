package pl.pwr.adam.zmuda.lab1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.adam.zmuda.lab1.adapters.CalculationAdapter
import pl.pwr.adam.zmuda.lab1.data.CalculationDatabase

class HistoryActivity : BaseActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        viewManager = LinearLayoutManager(this)

        val database = CalculationDatabase.getInstance(applicationContext)
        val dao = database.calculationDao()
        dao.load10Calculations().observe(this, Observer { history ->
            viewAdapter = CalculationAdapter(this, history)

            recyclerView = findViewById<RecyclerView>(R.id.historyRV).apply {
                setHasFixedSize(true)

                layoutManager = viewManager
                adapter = viewAdapter
            }

            findViewById<TextView>(R.id.noRecordsTV).visibility = if (history.count() > 0)  View.GONE else View.VISIBLE
        })

    }
}