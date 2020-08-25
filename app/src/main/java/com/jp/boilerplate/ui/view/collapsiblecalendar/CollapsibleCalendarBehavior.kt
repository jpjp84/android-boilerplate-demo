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

    private var initBottomY = 0
    private var savedMoveDistance = 0f
    private var firstDownY = 0f

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: RecyclerView,
        layoutDirection: Int
    ): Boolean {
        child.layoutParams?.let {
            parent.onLayoutChild(child, layoutDirection)
            ViewCompat.offsetTopAndBottom(child, 0)

            val bottom = findBottomLayout(parent.getDependencies(child))
            initBottomY = child.height
            ViewCompat.offsetTopAndBottom(bottom, initBottomY)
        }
        return super.onLayoutChild(parent, child, layoutDirection);
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
        savedMoveDistance = dependency.y - initBottomY
        child.y = savedMoveDistance
    }

    override fun onTouchEvent(
        parent: CoordinatorLayout,
        child: RecyclerView,
        ev: MotionEvent
    ): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> firstDownY = savedMoveDistance
            MotionEvent.ACTION_UP -> {
                (child[0] as? RecyclerView)?.getChildAt(0)?.measuredHeight?.let { childHeight ->
                    val animateDistance = getAnimateDistance(childHeight)
                    findBottomLayout(parent.getDependencies(child))
                        .animate()
                        .y(animateDistance)
                        .setDuration(250)
                        .start()
                }
            }
            else -> return false
        }
        return super.onTouchEvent(parent, child, ev)
    }

    private fun getAnimateDistance(childHeight: Int): Float {
        val isSwipeTopAndCollapse =
            abs(firstDownY) < abs(savedMoveDistance) && abs(firstDownY - savedMoveDistance) > 100
        val isSwipeBottomAndNotExpand =
            abs(firstDownY) > abs(savedMoveDistance) && abs(firstDownY - savedMoveDistance) < 100

        if (isSwipeTopAndCollapse || isSwipeBottomAndNotExpand) {
            return childHeight.toFloat()
        }

        val isSwipeBottomAndExpand =
            abs(firstDownY) > abs(savedMoveDistance) && abs(firstDownY - savedMoveDistance) > 100
        val isSwipeTopAndNotCollapse =
            abs(firstDownY) < abs(savedMoveDistance) && abs(firstDownY - savedMoveDistance) < 100

        if (isSwipeBottomAndExpand || isSwipeTopAndNotCollapse) {
            return initBottomY.toFloat()
        }

        return 0f
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: RecyclerView,
        ev: MotionEvent
    ): Boolean {
        onTouchEvent(parent, child, ev)
        return false
    }

    private fun findBottomLayout(dependencies: List<View>): View {
        dependencies.map {
            if (it is CollapsibleCalendarBottom) return it
        }
        throw IllegalStateException("Not have Bottom Layout")
    }
}
