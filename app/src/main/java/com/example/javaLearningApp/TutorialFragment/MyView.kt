package com.example.javaLearningApp.TutorialFragment

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

class MyView @JvmOverloads constructor(
    context: Context?,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyleAttr, defStyleRes) {

    private var wavePaint: Paint? = null
    private var rectPaint: Paint? = null
    private var textPaint: Paint? = null

    private var screenWidth = 0
    private var screenHeight = 0

    private val amplitude = 20
    private val startPoint = Point()
    private var path: Path? = null
    private var progress = 0f
    private var textProgress = 0f

    private fun init() {
        wavePaint = Paint()
        wavePaint!!.isAntiAlias = true
        wavePaint!!.strokeWidth = 1f

        rectPaint = Paint()
        rectPaint!!.isAntiAlias = true
        //rectPaint!!.color = Color.parseColor("#292929")
        rectPaint!!.strokeWidth = 10f
        rectPaint!!.style = Paint.Style.STROKE
        rectPaint!!.color = Color.GRAY

        textPaint = Paint()
        textPaint!!.style = Paint.Style.FILL
        textPaint!!.isAntiAlias = true
        textPaint!!.color = Color.WHITE
        textPaint!!.textSize = 50f
    }
    fun setProgress(progress: Float) {
        textProgress = progress
        if (progress == 100f) {
            this.progress = progress + amplitude
        } else {
            this.progress = progress
        }
        invalidate()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val dpWidth = 60 // Adjust the width in dp as needed
        val dpHeight = 160 // Adjust the height in dp as needed
        screenWidth = dpWidth.toPx(context)
        screenHeight = dpHeight.toPx(context)
        startPoint.x = -screenWidth
    }

    fun Int.toDp(context: Context): Int {
        return (this / context.resources.displayMetrics.density).toInt()
    }

    fun Int.toPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        clipRect(canvas)
        drawRect(canvas)
        drawWave(canvas)
        drawText(canvas)

        postInvalidateDelayed(10)
    }
    private fun drawText(canvas: Canvas) {
        val targetRect = Rect(0, -screenHeight, screenWidth, 0)
        val fontMetrics = textPaint!!.fontMetricsInt
        val baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2
        textPaint!!.textAlign = Paint.Align.CENTER

        val percentage = textProgress.toInt() // Convert textProgress to integer

        if (percentage > 50) {
            // Change text color to red if percentage is above 50
            textPaint!!.color = Color.WHITE
        } else {
            // Reset text color to default if percentage is 50 or below
            textPaint!!.color = Color.WHITE
        }

        canvas.drawText("$percentage%", targetRect.centerX().toFloat(), baseline.toFloat(), textPaint!!)
    }


    private fun drawWave(canvas: Canvas) {
        val height = (progress / 100 * screenHeight).toInt()
        startPoint.y = -height
        canvas.translate(0f, screenHeight.toFloat())
        path = Path()
        wavePaint!!.style = Paint.Style.FILL
        wavePaint!!.color = Color.parseColor("#393488")
        val wave = screenWidth / 4
        path!!.moveTo(startPoint.x.toFloat(), startPoint.y.toFloat())
        for (i in 0..3) {
            val startX = startPoint.x + i * wave * 2
            val endX = startX + 2 * wave
            if (i % 2 == 0) {
                path!!.quadTo(
                    ((startX + endX) / 2).toFloat(),
                    (startPoint.y + amplitude).toFloat(),
                    endX.toFloat(),
                    startPoint.y.toFloat()
                )
            } else {
                path!!.quadTo(
                    ((startX + endX) / 2).toFloat(),
                    (startPoint.y - amplitude).toFloat(),
                    endX.toFloat(),
                    startPoint.y.toFloat()
                )
            }
        }
        path!!.lineTo(screenWidth.toFloat(), (screenHeight / 2).toFloat())
        path!!.lineTo(-screenWidth.toFloat(), (screenHeight / 2).toFloat())
        path!!.lineTo(-screenWidth.toFloat(), 0f)
        path!!.close()
        canvas.drawPath(path!!, wavePaint!!)
        startPoint.x += 3 // Decreased the increment value for slower animation
        if (startPoint.x > 0) {
            startPoint.x = -screenWidth
        }
        path!!.reset()
    }

    private fun drawRect(canvas: Canvas) {
        val rectF = RectF(0f, 0f, screenWidth.toFloat(), screenHeight.toFloat())
        val cornerRadius = 100f // Set the corner radius to 100 pixels (adjust as needed)
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, rectPaint!!)
    }
    private fun clipRect(canvas: Canvas) {
        val rectF = RectF(0f, 0f, screenWidth.toFloat(), screenHeight.toFloat())
        val cornerRadius = 100f // Set the corner radius to match the rectangle's corner radius
        val rectPath = Path()
        rectPath.addRoundRect(rectF, cornerRadius, cornerRadius, Path.Direction.CW)
        canvas.clipPath(rectPath)
    }
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val parentWidth = MeasureSpec.getSize(widthMeasureSpec)
        val parentHeight = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(parentWidth, parentHeight)
    }

    private fun measureSize(defaultSize: Int, measureSpec: Int): Int {
        var result = defaultSize
        val mode = MeasureSpec.getMode(measureSpec)
        val size = MeasureSpec.getSize(measureSpec)
        when (mode) {
            MeasureSpec.UNSPECIFIED -> result = defaultSize
            MeasureSpec.AT_MOST, MeasureSpec.EXACTLY -> result = size
            else -> {}
        }
        return result
    }

    init {
        init()
    }
}
