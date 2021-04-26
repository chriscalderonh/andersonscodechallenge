package com.chriscalderonh.andersonscodechallenge.common.ui.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.chriscalderonh.andersonscodechallenge.databinding.ViewErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var binding: ViewErrorBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = ViewErrorBinding.inflate(inflater, this, true)
    }

    fun setClickListener(clickListener: () -> Unit) {
        binding.btnContinue.setOnClickListener {
            clickListener()
        }
    }
}

@BindingAdapter("errorMsg")
fun ErrorView.bindErrorMsg(errorMsg: String?) {
    errorMsg?.let { binding.errorText = it }
}