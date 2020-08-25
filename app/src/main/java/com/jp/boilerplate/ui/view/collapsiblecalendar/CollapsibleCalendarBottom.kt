package com.jp.boilerplate.ui.view.collapsiblecalendar

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.constraintlayout.widget.ConstraintLayout
import com.orhanobut.logger.Logger

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
    var dY: Float = 0f
    var lastAction = 0

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                dX = this.x - event.rawX
                dY = this.y - event.rawY
                lastAction = MotionEvent.ACTION_DOWN
            }
            MotionEvent.ACTION_MOVE -> {
                this.y = event.rawY + dY
            }
            MotionEvent.ACTION_UP -> {
                performClick()
            }
            else -> return false
        }
        return true
    }

    override fun performClick(): Boolean {
        return super.performClick()
    }

    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
        return true
    }
}