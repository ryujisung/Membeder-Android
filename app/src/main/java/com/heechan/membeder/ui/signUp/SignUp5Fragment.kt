package com.heechan.membeder.ui.signUp

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.heechan.membeder.R
import com.heechan.membeder.base.BaseFragment
import com.heechan.membeder.databinding.FragmentSignUp4Binding
import com.heechan.membeder.databinding.FragmentSignUp5Binding

class SignUp5Fragment : BaseFragment<FragmentSignUp5Binding>(R.layout.fragment_sign_up_5) {
    val viewModel : SignUpViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel

        binding.next.setOnClickListener {
            viewModel.signUp()
        }
    }
}