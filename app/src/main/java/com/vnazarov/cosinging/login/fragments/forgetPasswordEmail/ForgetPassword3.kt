package com.vnazarov.cosinging.login.fragments.forgetPasswordEmail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.vnazarov.cosinging.MainActivity
import com.vnazarov.cosinging.R

class ForgetPassword3 : Fragment() {

    private lateinit var etPassword: EditText
    private lateinit var etConfirmPassword: EditText
    private lateinit var confirmButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password_3, container, false)

        defineElements(view)

        confirmButton.setOnClickListener {
            val intent = MainActivity.newIntent(requireContext())
            startActivity(intent)
        }

        return view
    }

    private fun defineElements(view: View) {
        etPassword = view.findViewById(R.id.recovery_3_password) as EditText

        etConfirmPassword = view.findViewById(R.id.recovery_3_confirm_password) as EditText

        confirmButton = view.findViewById(R.id.recovery_3_confirm_button) as Button

        defineTextWatcher()
    }

    private fun defineTextWatcher() {
        if (etPassword.text.isEmpty() && etConfirmPassword.text.isEmpty()) {
            confirmButton.isEnabled = false
        }

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val passwordText = etPassword.text.toString()
                val confirmPasswordText = etConfirmPassword.text.toString()

                confirmButton.isEnabled =
                    passwordText.isNotEmpty() && confirmPasswordText.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        etPassword.addTextChangedListener(textWatcher)
        etConfirmPassword.addTextChangedListener(textWatcher)
    }
}