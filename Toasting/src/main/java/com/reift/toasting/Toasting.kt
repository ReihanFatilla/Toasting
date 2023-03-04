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
    private lateinit var toastIcon: ImageView

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
        toastIcon = v.findViewById(R.id.toast_icon)
        return v
    }

    class Builder() {

        var onButtonClicked = {}
        var title = "Success!"
        var content = "Finished showing the toasting"

        fun setTitleText(text: String){
            title = text
        }

        fun setContentText(text: String){
            content = text
        }

        fun setOnButtonClick(onClick: () -> Unit){
            onButtonClicked = onClick
        }

        fun show(fragmentManager: FragmentManager){
            Toasting(this).show(fragmentManager, null)
        }

    }
}