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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vnazarov.cosinging.R

class ForgetPassword2: Fragment() {

    private lateinit var etCode: EditText
    private lateinit var nextButton: Button
    private lateinit var textEmail: TextView
    private var email = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password_2, container, false)

        defineElements(view)

        loadData()

        nextButton.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_forgetPassword2_to_forgetPassword3)
        }

        return view
    }

    private fun defineElements(view: View){
        etCode = view.findViewById(R.id.recovery_2_code) as EditText

        nextButton = view.findViewById(R.id.recovery_2_next_button) as Button

        textEmail = view.findViewById(R.id.recovery_2_text) as TextView

        defineTextWatcher()
    }

    private fun loadData(){
        arguments?.let { bundle ->
            email = bundle.getString("email") as String
            textEmail.setText("Recovery code has been sent to your email address ${email}. Enter code below")
        }
    }

    private fun defineTextWatcher(){
        if (etCode.text.isEmpty()){
            nextButton.isEnabled = false
        }

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailInput = etCode.text.toString()

                nextButton.isEnabled = emailInput.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        etCode.addTextChangedListener(textWatcher)
    }
}