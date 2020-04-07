package xyz.ecbn.moviemvvm.utils

import android.content.Context
import android.util.AttributeSet

/**
 * MovieAppMVVM Created by ecbn on 29/03/20.
 */
class SquareImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : androidx.appcompat.widget.AppCompatImageView(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width: Int = measuredWidth / 4
        setMeasuredDimension(width * 4, width * 3)
    }
}