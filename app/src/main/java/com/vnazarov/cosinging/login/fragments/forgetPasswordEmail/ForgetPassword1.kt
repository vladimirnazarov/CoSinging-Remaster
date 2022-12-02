package com.vnazarov.cosinging.login.fragments.forgetPasswordEmail

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vnazarov.cosinging.R

class ForgetPassword1: Fragment() {

    private lateinit var etEmail: EditText
    private lateinit var nextButton: Button
    private lateinit var forgetLogin: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password_1, container, false)

        defineElements(view)

        forgetLogin.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_forgetPassword1_to_forgetPasswordPhone)
        }

        nextButton.setOnClickListener {
            val bundle = bundleOf("email" to etEmail.text.toString())
            Navigation.findNavController(requireView()).navigate(R.id.action_forgetPassword1_to_forgetPassword2, bundle)
        }

        return view
    }

    private fun defineElements(view: View){
        etEmail = view.findViewById(R.id.recovery_1_login) as EditText

        nextButton = view.findViewById(R.id.recovery_1_next_button) as Button

        forgetLogin = view.findViewById(R.id.recovery_1_forget) as TextView
        forgetLogin.isClickable = true
        defineTextWatcher()
    }

    private fun defineTextWatcher(){
        if (etEmail.text.isEmpty()){
            nextButton.isEnabled = false
        }

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailInput = etEmail.text.toString()

                nextButton.isEnabled = emailInput.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        etEmail.addTextChangedListener(textWatcher)
    }
}