package pl.pwr.adam.zmuda.lab1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import pl.pwr.adam.zmuda.lab1.databinding.ActivityDetailsBinding
import java.util.*


class DetailsActivity : AppCompatActivity() {

    companion object {
        const val BMI_PARAM: String = "BMI_PARAM"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bmi = intent.getFloatExtra(BMI_PARAM, -1f)
        if (bmi < 0)
            throw IllegalArgumentException("DetailsActivity: No bmi supplied in intent!")

        val bmiText = String.format(Locale.US, "%.1f", bmi)

        val header = getString(R.string.bmi_details_header, bmiText, getBmiTitle(bmi))
        val description = getBmiDescription(bmi)
        val text = header + "\n\n" + description
        binding.bmiInfoTV.text = text
    }

    fun moreInfo(view: View) {
        val url = "https://en.wikipedia.org/wiki/Body_mass_index"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    private fun getBmiDescription(bmi: Float): String {
        return when {
            bmi < 18.5 -> getString(R.string.details_description_underweight)
            bmi < 25 -> getString(R.string.details_description_normal_weight)
            bmi < 30 -> getString(R.string.details_description_overweight)
            else -> getString(R.string.details_description_obese)
        }
    }

    private fun getBmiTitle(bmi: Float): String {
        return when {
            bmi < 18.5 -> getString(R.string.details_title_underweight)
            bmi < 25 -> getString(R.string.details_title_normal_weight)
            bmi < 30 -> getString(R.string.details_title_overweight)
            else -> getString(R.string.details_title_obese)
        }
    }
}