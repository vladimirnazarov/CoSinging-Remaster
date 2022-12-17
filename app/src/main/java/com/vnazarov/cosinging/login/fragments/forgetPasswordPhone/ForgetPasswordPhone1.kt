package com.vnazarov.cosinging.login.fragments.forgetPasswordPhone

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.vnazarov.cosinging.R

class ForgetPasswordPhone1: Fragment() {

    private lateinit var etPhone: EditText
    private lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password_phone_1, container, false)

        defineElements(view)

        nextButton.setOnClickListener {
            val bundle = bundleOf("phone" to etPhone.text.toString())
            Navigation.findNavController(requireView()).navigate(R.id.action_forgetPasswordPhone_to_forgetPasswordPhone2, bundle)
        }

        return view
    }

    private fun defineElements(view: View){
        etPhone = view.findViewById(R.id.recovery_phone_login) as EditText

        nextButton = view.findViewById(R.id.recovery_phone_next_button) as Button

        defineTextWatcher()
    }

    private fun defineTextWatcher(){
        if (etPhone.text.isEmpty()){
            nextButton.isEnabled = false
        }

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailInput = etPhone.text.toString()

                nextButton.isEnabled = emailInput.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        etPhone.addTextChangedListener(textWatcher)
    }
}