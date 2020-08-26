package com.jp.boilerplate.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger


class CollapsibleCalendar : RecyclerView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
//
//    var dX: Float = 0f
//    var dY: Float = 0f
//    var lastAction = 0
//
//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        when (event.actionMasked) {
//            MotionEvent.ACTION_DOWN -> {
//                dX = this.x - event.getRawX()
//                dY = this.y - event.getRawY()
//                lastAction = MotionEvent.ACTION_DOWN
//            }
//            MotionEvent.ACTION_MOVE -> {
//                this.animate()
//                    .x(event.rawX + dX - (this.width / 2))
//                    .y(event.rawY + dY - (this.height / 2))
//                    .setDuration(0)
//                    .start();
//            }
//            MotionEvent.ACTION_UP ->  {
//                Logger.d("Up Action!!")
//            }
//            else -> return false
//        }
//        return true
//    }
//
//    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//        return true
//    }
}
