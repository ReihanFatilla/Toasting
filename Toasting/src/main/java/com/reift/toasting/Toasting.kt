package com.reift.toasting


import android.graphics.Color
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


class Toasting private constructor(builder: Builder): DialogFragment() {

    private val title = builder.title
    private val content = builder.content
    private val onButtonClicked = builder.onButtonClicked
    private val icon = builder.icon
    private val buttonText = builder.buttonText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val v: View = inflater.inflate(R.layout.toasting_ui, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        v.findViewById<TextView>(R.id.tv_title).text = title
        v.findViewById<TextView>(R.id.tv_content).text = content
        v.findViewById<MaterialButton>(R.id.btn_action).setOnClickListener {
            dismiss()
            onButtonClicked
        }
        v.findViewById<ImageView>(R.id.toast_icon).setImageResource(icon)
        v.findViewById<MaterialButton>(R.id.btn_action).text = buttonText
        return v
    }

    class Builder(type: String = Toasting.SUCCESS_TYPE) {

        var onButtonClicked = {}
        var title = when(type){
            WARNING_TYPE -> "Warning!"
            ERROR_TYPE -> "Error!"
            else -> "Success!"
        }

        var buttonText = "Got it"

        var content = when(type){
            WARNING_TYPE -> "Finished showing Warning toasting"
            ERROR_TYPE -> "Finished showing Error toasting"
            else -> "Finished showing Success toasting"
        }

        var icon = when(type){
            WARNING_TYPE -> R.drawable.ic_warning
            ERROR_TYPE -> R.drawable.ic_error
            else -> R.drawable.ic_success
        }

        fun setTitleText(text: String){
            title = text
        }

        fun setContentText(text: String){
            content = text
        }

        fun setOnButtonClick(onClick: () -> Unit){
            onButtonClicked = onClick
        }

        fun setButtonMessage(text: String){
            buttonText = text
        }

        fun setDrawableIcon(drawable: Int){
            icon = drawable
        }

        fun show(fragmentManager: FragmentManager){
            Toasting(this).show(fragmentManager, null)
        }

    }

    companion object{
        const val WARNING_TYPE = "warning_type"
        const val SUCCESS_TYPE = "success_type"
        const val ERROR_TYPE = "error_type"
    }
}