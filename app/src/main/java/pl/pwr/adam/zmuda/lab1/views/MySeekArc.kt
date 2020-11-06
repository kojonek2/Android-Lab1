package pl.pwr.adam.zmuda.lab1.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import com.triggertrap.seekarc.SeekArc

class MySeekArc : SeekArc {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?) : super(context)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        return true
    }
}