package com.heechan.membeder.view

import android.view.LayoutInflater
import android.view.View
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.google.android.material.snackbar.Snackbar
import com.heechan.membeder.R
import com.heechan.membeder.databinding.SnackbarGoodBinding

class CustomSnackBar(
    private val parent: View,
    private val title: String,
    private val message: String,
    private val duration: Int,
    private val type: SnackBarType,
) {

    companion object {
        fun make(
            view: View,
            title: String,
            message: String,
            duration: Int = 3000,
            type: SnackBarType
        ) = CustomSnackBar(view, title, message, duration, type)
    }

    private val context = parent.context
    private val snackBar = Snackbar.make(parent, "", duration)
    private val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout

    private val snackBarLayoutRes = when (type) {
        SnackBarType.GOOD -> R.layout.snackbar_good
        else -> R.layout.snackbar_bad
    }

    private val inflater = LayoutInflater.from(context)
    private val binding: SnackbarGoodBinding =
        DataBindingUtil.inflate(inflater, snackBarLayoutRes, null, false)

    init {
        initView()
        initData()
    }

    private fun initView() {
        with(snackBarLayout) {
            removeAllViews()
            setPadding(16, 16, 16, 16)
            setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))
            addView(binding.root, 0)
        }
    }

    private fun initData() {
        with(binding) {
            txtGoodSnackBarTitle.text = title
            txtGoodSnackBarMessage.text = message
        }
    }

    fun show() {
        snackBar.show()
    }

    fun setAction(actionBtnTitle: String, eventListener: View.OnClickListener): Unit {
        binding.txtGoodSnackBarActionBtn.apply {
            visibility = View.VISIBLE
            text = actionBtnTitle
            setOnClickListener {
                snackBar.dismiss()
                eventListener.onClick(it)
            }
        }
    }

}