package com.himanshu.activitylifecycle

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var etMobileNumber: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var txtForgotPassword: TextView
    private lateinit var txtRegister: TextView

    private var validMobileNumber = "avengers@starkindustries.com"
    private var validPassword =
        arrayOf("tony", "steve", "bruce", "t\'chala", "natasha", "thor", "thanos")

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        // creating an activity instances
        super.onCreate(savedInstanceState)

        // creating sharedPreferences
        sharedPreferences = getSharedPreferences(
            R.string.preference_file_name.toString(),
            MODE_PRIVATE
        )
        // creating a global intent for this function
        val intent = Intent(this@LoginActivity, AvengersActivity::class.java)

        // getting value from preference for login check
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        if (isLoggedIn) {
            startActivity(intent)
        } else {
            // setting xml to the screen and giving tittle
            setContentView(R.layout.activity_login)
            title = "LOGIN"
            // getting value of views
            etMobileNumber = findViewById(R.id.etPhoneNumber)
            etPassword = findViewById(R.id.etPassword)
            btnLogin = findViewById(R.id.btnLogin)
            txtRegister = findViewById(R.id.txtRegister)
            txtForgotPassword = findViewById(R.id.txtForgotPassword)


            btnLogin.setOnClickListener {
                val mobileNumber = this.etMobileNumber.text.toString()
                val password = this.etPassword.text.toString()

                val checkMobileNumber = validMobileNumber.contains(mobileNumber)
                val checkPassword = validPassword.contains(password)

                if (!checkMobileNumber) {
                    Toast.makeText(
                        this@LoginActivity,
                        "MobileNumber is Invalid!!",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else if (!checkPassword) {
                    Toast.makeText(this@LoginActivity, "Password is Invalid!!", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    val nameOfAvenger = when (password) {
                        validPassword[0] -> "IRON MAN"
                        validPassword[1] -> "CAPTAIN AMERICA"
                        validPassword[2] -> "HULK"
                        validPassword[3] -> "BLACK PANTHER"
                        validPassword[4] -> "BLACK WIDOW"
                        validPassword[5] -> "THOR"
                        else -> "AVENGERS"
                    }
                    savePreferences(nameOfAvenger)
                    finish()
                    startActivity(intent)
                }
            }

            txtRegister.setOnClickListener {
                Toast.makeText(this@LoginActivity, "You can't register!!", Toast.LENGTH_SHORT)
                    .show()
            }

            txtForgotPassword.setOnClickListener {
                Toast.makeText(this@LoginActivity, "Oops!! you forgot password", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (sharedPreferences.getBoolean("isLoggedIn", false)) finish()
    }

    private fun savePreferences(title: String) {
        sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
        sharedPreferences.edit().putString("title", title).apply()
    }

}