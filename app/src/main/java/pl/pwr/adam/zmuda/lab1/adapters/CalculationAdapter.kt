package pl.pwr.adam.zmuda.lab1.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pl.pwr.adam.zmuda.lab1.MainActivity
import pl.pwr.adam.zmuda.lab1.R
import pl.pwr.adam.zmuda.lab1.data.Calculation
import java.text.SimpleDateFormat
import java.util.*

class CalculationAdapter(private val context: Context, private val myDataSet: List<Calculation>) : RecyclerView.Adapter<CalculationAdapter.CalculationViewHolder>() {

    class CalculationViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        val dateTV: TextView = view.findViewById(R.id.dateTV)
        val bmiTV: TextView = view.findViewById(R.id.bmiTV)
        val massTV: TextView = view.findViewById(R.id.massTV)
        val heightTV: TextView = view.findViewById(R.id.heightTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_recycleview_row, parent, false)
        return CalculationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return myDataSet.size
    }

    override fun onBindViewHolder(holder: CalculationViewHolder, position: Int) {
        val calculation = myDataSet[position]

        val date = Date(calculation.timeStamp)
        val format = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.UK)
        holder.dateTV.text = format.format(date)

        val heightText = if (calculation.selectedUnit == MainActivity.Units.METRIC)
            context.getString(R.string.unit_symbol_height_metric, calculation.height)
        else
            context.getString(R.string.unit_symbol_height_imperial, calculation.height)

        val massText = if (calculation.selectedUnit == MainActivity.Units.METRIC)
            context.getString(R.string.unit_symbol_mass_metric, calculation.mass)
        else
            context.getString(R.string.unit_symbol_mass_imperial, calculation.mass)

        holder.bmiTV.text = calculation.bmi
        holder.heightTV.text = heightText
        holder.massTV.text = massText
    }
}