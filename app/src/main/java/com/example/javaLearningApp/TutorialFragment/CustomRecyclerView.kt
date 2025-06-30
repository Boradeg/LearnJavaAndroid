package com.example.javaLearningApp.TutorialFragment
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class CustomRecyclerView : RecyclerView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle)

    override fun onInterceptTouchEvent(e: MotionEvent): Boolean {
        // Disable intercepting touch events for certain actions, like scrolling vertically
        when (e.action) {
            MotionEvent.ACTION_DOWN -> {
                // Return false to indicate that the touch event was not intercepted
                // and should be passed to the child views
                return false
            }
        }
        // Return false to allow the RecyclerView to handle the touch event
        return false
    }
}
