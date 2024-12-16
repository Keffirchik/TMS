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

class CustomView(private val myContext: Context, private val attrs: AttributeSet?) :
    View(myContext, attrs) {

    private var count: Int? = null
    private var image: AppCompatImageView? = null
    private var customText: String = ""
    private val paintWhite = Paint()
    private val paintRed = Paint()

    init {
        initAttrs(attrs)
        initImage()
    }

    private fun initImage() {
        image = AppCompatImageView(context, attrs)
        val params = image?.layoutParams
        params?.height = 60
    }

    fun setDrawable(drawable: Drawable) {
        image?.setImageDrawable(drawable)
    }

    fun setCount(count: Int) {
        this.count = count
    }

    fun showError(text: String) {
        this.customText = text
        this.isVisible = true
    }

    private fun initAttrs(attrs: AttributeSet?) {
        val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomView)
        val count = typedArray.getInt(R.styleable.CustomView_customViewValue, 0)
        if (count == 0) {
            this.isVisible = false
        }
        typedArray.recycle()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paintWhite.color = Color.WHITE
        paintRed.color = Color.RED
        paintWhite.style = Paint.Style.FILL
        paintRed.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
//        canvas.drawCircle(10F, 10F, 10F, paint)
//        canvas.drawRect(0f, 0f, 600f, 400f, paintWhite)
        canvas.drawColor(Color.WHITE)
        canvas.drawText(customText, 50F, 60F, paintRed)
        super.onDraw(canvas)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }
}