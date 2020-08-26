package com.jp.boilerplate.ui.view.collapsiblecalendar

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout

class CollapsibleCalendarBottom : ConstraintLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes)

    var dX: Float = 0f
    var offsetBottomLayoutY: Float = 0f
    var maximumScrollY = 0f
    var minimumScrollY = 0f

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            maximumScrollY = y
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                offsetBottomLayoutY = this.y - event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val calculatedY = event.rawY + offsetBottomLayoutY
                if (overflowScrollRange(calculatedY)) {
                    return true
                }
                this.y = calculatedY
            }
            else -> {
                return true
            }
        }
        return true
    }

    private fun overflowScrollRange(distance: Float): Boolean {
        return minimumScrollY > distance || maximumScrollY < distance
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return true
    }
}
