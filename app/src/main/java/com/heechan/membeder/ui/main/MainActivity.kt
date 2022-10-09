package com.heechan.membeder.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseActivity
import com.heechan.membeder.databinding.ActivityMainBinding
import com.heechan.membeder.ui.SplashActivity
import com.heechan.membeder.view.AlertDialog
import com.heechan.membeder.view.TagView

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnMainFill.setOnClickListener {
            val okCallBack: () -> Unit = {
                Toast.makeText(this, "확인 클릭", Toast.LENGTH_SHORT).show()
            }

            val dialog = AlertDialog(title = "저는 제목 입니다", message = "저는 설명문입니다.", okCallBack = okCallBack).apply {
                show(supportFragmentManager, "alert dialog")
            }
        }
    }
}