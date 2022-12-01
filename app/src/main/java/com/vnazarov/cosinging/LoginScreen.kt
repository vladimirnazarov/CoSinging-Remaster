package com.vnazarov.cosinging

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LoginScreen : AppCompatActivity() {

    private lateinit var signIn: TextView

    private lateinit var loginButton: Button
    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        findElements()

        signIn.setOnClickListener {
            createSignInDialog(this@LoginScreen)
        }

        loginButton.setOnClickListener {
            val intent = MainActivity.newIntent(this@LoginScreen)
            startActivity(intent)
        }
    }

    private fun findElements() {
        signIn = findViewById(R.id.login_screen_sign_in_text)
        signIn.isClickable = true

        etLogin = findViewById(R.id.login_screen_et_login)
        etPassword = findViewById(R.id.login_screen_et_password)

        loginButton = findViewById(R.id.login_screen_login_button)
        defineLoginTextWatcher()
    }

    /**
     * Set the dialog to the max width of a screen
     */

    private fun createSignInDialog(packageContext: Context) {
        val dialogBinding = layoutInflater.inflate(R.layout.activity_sign_in_dialog, null)

        val myDialog = Dialog(packageContext)
        myDialog.setContentView(dialogBinding)

        myDialog.setCancelable(true)
        myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        myDialog.show()

        val etSILogin = dialogBinding.findViewById<EditText>(R.id.sign_in_login)
        val etSIPassword = dialogBinding.findViewById<EditText>(R.id.sign_in_password)
        val etSIConfirmPassword = dialogBinding.findViewById<EditText>(R.id.sign_in_confirm_password)

        val noButton = dialogBinding.findViewById<Button>(R.id.sign_in_no)
        noButton.setOnClickListener {
            myDialog.dismiss()
        }

        val yesButton = dialogBinding.findViewById<Button>(R.id.sign_in_yes)
        yesButton.setOnClickListener {
            val intent = MainActivity.newIntent(this@LoginScreen)
            startActivity(intent)
        }

        if (etSILogin.text.isEmpty() && etSIPassword.text.isEmpty() && etSIConfirmPassword.text.isEmpty()){
            yesButton.isEnabled = false
        }

        val signInTextWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailInput = etSILogin.text.toString()
                val passwordInput = etSIPassword.text.toString()
                val confirmPassword = etSIConfirmPassword.text.toString()

                yesButton.isEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty() && confirmPassword.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        etSILogin.addTextChangedListener(signInTextWatcher)
        etSIPassword.addTextChangedListener(signInTextWatcher)
        etSIConfirmPassword.addTextChangedListener(signInTextWatcher)
    }

    private fun defineLoginTextWatcher(){
        if (etLogin.text.isEmpty() && etPassword.text.isEmpty()){
            loginButton.isEnabled = false
        }

        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val emailInput = etLogin.text.toString()
                val passwordInput = etPassword.text.toString()

                loginButton.isEnabled = emailInput.isNotEmpty() && passwordInput.isNotEmpty()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        etLogin.addTextChangedListener(textWatcher)
        etPassword.addTextChangedListener(textWatcher)
    }
}