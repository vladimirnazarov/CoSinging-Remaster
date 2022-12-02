package com.vnazarov.cosinging.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.Navigation
import com.vnazarov.cosinging.login.fragments.LoginScreenMainFragment
import com.vnazarov.cosinging.R

class LoginScreen : AppCompatActivity() {

    private lateinit var signIn: TextView

    private lateinit var loginButton: Button
    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var forgetPassword: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.login_screen_fragment)
        val navController = Navigation.findNavController(this, R.id.login_screen_fragment)

        val loginFragment = LoginScreenMainFragment.newInstance()

        if (currentFragment == null){
            supportFragmentManager.beginTransaction().add(R.id.login_screen_fragment, loginFragment).commit()
        }
    }
}