package com.reift.toasting


import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.button.MaterialButton
import com.reift.toasting.utils.Animator.jumpAnimation


class Toasting private constructor(builder: Builder) : DialogFragment() {

    private val title = builder.title
    private val content = builder.content
    private val onButtonClicked = builder.onButtonClicked
    private val icon = builder.icon
    private val buttonText = builder.buttonText
    private val titleFont= builder.titleFont
    private val contentFont = builder.contentFont
    private val buttonFont = builder.buttonFont

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.toasting_ui, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        v.findViewById<TextView>(R.id.tv_title).apply {
            text = title
            titleFont?.let { typeface = it }

        }
        v.findViewById<TextView>(R.id.tv_content).apply {
            text = content
            contentFont?.let { typeface = it }
        }
        v.findViewById<ImageView>(R.id.toast_icon).apply {
            setImageResource(icon)
            jumpAnimation()
        }
        v.findViewById<MaterialButton>(R.id.btn_action).apply {
            text = buttonText
            buttonFont?.let { typeface = it }
            setOnClickListener {
                dismiss()
                onButtonClicked()
            }
        }
        return v
    }

    class Builder(type: String = SUCCESS_TYPE) {

        var onButtonClicked = {

        }

        var buttonText = "Got it"

        var titleFont: Typeface? = null
        var contentFont: Typeface? = null
        var buttonFont: Typeface? = null

        var title = when (type) {
            WARNING_TYPE -> "Warning!"
            ERROR_TYPE -> "Error!"
            else -> "Success!"
        }

        var content = when (type) {
            WARNING_TYPE -> "Finished showing Warning toasting"
            ERROR_TYPE -> "Finished showing Error toasting"
            else -> "Finished showing Success toasting"
        }

        var icon = when (type) {
            WARNING_TYPE -> R.drawable.ic_warning
            ERROR_TYPE -> R.drawable.ic_error
            else -> R.drawable.ic_success
        }

        fun setTitleText(text: String): Builder {
            title = text
            return this
        }

        fun setContentText(text: String): Builder {
            content = text
            return this
        }

        fun setOnButtonClick(onClick: () -> Unit): Builder {
            onButtonClicked = onClick
            return this
        }

        fun setButtonMessage(text: String): Builder {
            buttonText = text
            return this
        }

        fun setDrawableIcon(drawable: Int): Builder {
            icon = drawable
            return this
        }

        fun setTitleFont(font: Typeface?): Builder {
            titleFont = font
            return this
        }

        fun setContentFont(font: Typeface?): Builder {
            contentFont = font
            return this
        }

        fun setButtonFont(font: Typeface?): Builder {
            buttonFont = font
            return this
        }

        fun show(fragmentManager: FragmentManager): Builder {
            Toasting(this).show(fragmentManager, null)
            return this
        }


    }

    companion object {
        const val WARNING_TYPE = "warning_type"
        const val SUCCESS_TYPE = "success_type"
        const val ERROR_TYPE = "error_type"
    }
}