package id.pamoyanan_dev.l_extras.util.widget

import android.content.Context
import android.graphics.Typeface
import android.support.design.widget.TextInputLayout
import android.text.TextPaint
import android.util.AttributeSet

class CustomFontTextInputLayout : TextInputLayout {

    constructor(context: Context) : super(context) {
        initFont(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initFont(context)
    }

    private fun initFont(context: Context) {
        val typeface = Typeface.createFromAsset(
            context.assets, "fonts/fs_joey_bold.otf"
        )

        val editText = editText
        if (editText != null) {
            editText.typeface = typeface
        }
        try {
            // Retrieve the CollapsingTextHelper Field
            val cthf = TextInputLayout::class.java.getDeclaredField("mCollapsingTextHelper")
            cthf.isAccessible = true

            // Retrieve an instance of CollapsingTextHelper and its TextPaint
            val cth = cthf.get(this)
            val tpf = cth.javaClass.getDeclaredField("mTextPaint")
            tpf.isAccessible = true

            // Apply your Typeface to the CollapsingTextHelper TextPaint
            (tpf.get(cth) as TextPaint).typeface = typeface
        } catch (ignored: Exception) {
            // Nothing to do
        }

    }
}