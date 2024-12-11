package com.example.tms.presentation.view

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import com.example.tms.R

class CustomView(private val myContext: Context, private val attrs: AttributeSet?) : View(myContext, attrs) {

    private var count: Int? = null
    private var image: AppCompatImageView? = null

    init {
        initAttrs(attrs)
        initImage()
    }

    private fun initImage() {
        image = AppCompatImageView(context, attrs)
        val params = image?.layoutParams
        params?.height = 60

    }

    fun setDrawable(drawable: Drawable){
     image?.setImageDrawable(drawable)
    }

    fun setCount(count: Int) {
        this.count = count
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        val count = typedArray.getInt(R.styleable.CustomView_customViewValue, 0)
        if (count == 0) {
            this.isVisible = false
        }
        typedArray.recycle()
    }

    val paint = Paint()
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint.setColor(Color.RED)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(10F, 10F, 10F, paint)
        super.onDraw(canvas)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}