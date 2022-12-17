package com.vnazarov.cosinging.login.fragments.forgetPasswordPhone

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

class ForgetPasswordPhone2: Fragment() {

    private lateinit var etCode: EditText
    private lateinit var nextButton: Button
    private lateinit var textPhone: TextView
    private var phone = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password_phone_2, container, false)

        defineElements(view)

        loadData()

        nextButton.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_forgetPasswordPhone2_to_forgetPassword3)
        }

        return view
    }

    private fun defineElements(view: View){
        etCode = view.findViewById(R.id.recovery_phone_2_code) as EditText

        nextButton = view.findViewById(R.id.recovery_phone_2_next_button) as Button

        textPhone = view.findViewById(R.id.recovery_phone_2_text) as TextView

        defineTextWatcher()
    }

    private fun loadData(){
        arguments?.let { bundle ->
            phone = bundle.getString("phone") as String
            textPhone.setText("Recovery code has been sent to your phone number $phone. Enter code below")
        }
    }

    private fun defineTextWatcher(){
        if (etCode.text.isEmpty()){
            nextButton.isEnabled = false
        }

        val textWatcher: TextWatcher = object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val code = etCode.text.toString()

                nextButton.isEnabled = code.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}

        }

        etCode.addTextChangedListener(textWatcher)
    }
}