package com.jp.boilerplate.ui.view.collapsiblecalendar

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
import androidx.core.view.ViewCompat
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class CollapsibleCalendarBehavior(context: Context?, attrs: AttributeSet?) :
    Behavior<RecyclerView>(context, attrs) {

    companion object {
        const val MINIMUM_MOVE_OFFSET = 100
    }

    private var initBottomY = 0
    private var initBottomLayoutY = 0f
    private lateinit var bottomLayout: View

    var offsetBottomLayoutY: Float = 0f
    var calendarRowHeight = 0

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: RecyclerView,
        layoutDirection: Int
    ): Boolean {
        getChildRowHeight(child)?.let { childRowHeight ->
            parent.onLayoutChild(child, layoutDirection)
            ViewCompat.offsetTopAndBottom(child, 0)

            bottomLayout = findBottomLayout(parent.getDependencies(child)).apply {
                layout(0, initBottomY, parent.width, initBottomY + height)
            }
            initBottomY = child.height
            calendarRowHeight = childRowHeight
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    private fun getChildRowHeight(child: RecyclerView): Int? {
        if (child.layoutParams == null || child.childCount == 0) {
            return null
        }

        return (child[0] as? RecyclerView)?.getChildAt(0)?.measuredHeight
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {
        return dependency is CollapsibleCalendarBottom
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: RecyclerView,
        dependency: View
    ): Boolean {
        scrollByDependency(child, dependency)
        return super.onDependentViewChanged(parent, child, dependency)
    }

    private fun scrollByDependency(child: View, dependency: View) {
        child.y = dependency.y - initBottomY
    }

    override fun onTouchEvent(
        parent: CoordinatorLayout,
        child: RecyclerView,
        event: MotionEvent
    ): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                offsetBottomLayoutY = bottomLayout.y - event.rawY
                initBottomLayoutY = bottomLayout.y
            }
            MotionEvent.ACTION_MOVE -> {
                val calculatedBottomY = event.rawY + offsetBottomLayoutY
                if (isOverflowRange(calculatedBottomY)) {
                    return true
                }
                bottomLayout.y = calculatedBottomY
            }
            MotionEvent.ACTION_UP -> {
                if (isOverflowRange(bottomLayout.y)) {
                    return true
                }

                startViewEdgeStickAnim(parent, child)
            }
            else -> return false
        }
        return true
    }

    private fun isOverflowRange(distance: Float): Boolean {
        return distance >= initBottomY || distance <= calendarRowHeight
    }

    private fun startViewEdgeStickAnim(parent: CoordinatorLayout, child: RecyclerView) {
        if (!::bottomLayout.isInitialized) {
            bottomLayout = findBottomLayout(parent.getDependencies(child))
        }

        (child[0] as? RecyclerView)?.getChildAt(0)?.measuredHeight?.let { childHeight ->
            bottomLayout
                .animate()
                .y(getAnimateDistance(childHeight))
                .setDuration(250)
                .start()
        }
    }

    private fun getAnimateDistance(childHeight: Int): Float {
        val isSwipeTop = initBottomLayoutY > bottomLayout.y
        val isReachMoveOffset = abs(initBottomLayoutY - bottomLayout.y) > MINIMUM_MOVE_OFFSET

        if ((isSwipeTop && isReachMoveOffset) || (!isSwipeTop && !isReachMoveOffset)) {
            return childHeight.toFloat()
        }

        if ((!isSwipeTop && isReachMoveOffset) || (isSwipeTop && !isReachMoveOffset)) {
            return initBottomY.toFloat()
        }

        return bottomLayout.y
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: RecyclerView,
        ev: MotionEvent
    ): Boolean {
        return true
    }

    private fun findBottomLayout(dependencies: List<View>): View {
        dependencies.map {
            if (it is CollapsibleCalendarBottom) return it
        }
        throw IllegalStateException("Not have Bottom Layout")
    }
}
