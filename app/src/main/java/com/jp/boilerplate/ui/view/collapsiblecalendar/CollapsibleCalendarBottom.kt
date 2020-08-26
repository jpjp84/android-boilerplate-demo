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
    var initY = 0f

//    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
//        super.onLayout(changed, left, top, right, bottom)
//        if (changed) {
//            initY = y
//        }
//    }
//
//    override fun onTouchEvent(event: MotionEvent?): Boolean {
//        when (event?.actionMasked) {
//            MotionEvent.ACTION_DOWN -> {
//                dX = this.x - event.rawX
//                dY = this.y - event.rawY
//                lastAction = MotionEvent.ACTION_DOWN
//            }
//            MotionEvent.ACTION_MOVE -> {
//                val distance = event.rawY + dY
//                if (distance < 144) {
//                    return false
//                }
//                if (initY < distance) {
//                    return false
//                }
//                this.y = event.rawY + dY
//            }
//            MotionEvent.ACTION_UP -> {
//                performClick()
//            }
//            else -> {
//                return true
//            }
//        }
//        return true
//    }
//
//    override fun performClick(): Boolean {
//        return super.performClick()
//    }
//
//    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//        return true
//    }
}
