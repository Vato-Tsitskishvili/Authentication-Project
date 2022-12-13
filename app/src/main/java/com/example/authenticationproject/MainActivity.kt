package com.example.authenticationproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var email: TextInputLayout
    private lateinit var password: TextInputLayout
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email = findViewById(R.id.editTextEmail)
        password = findViewById(R.id.editTextPassword)
        button = findViewById(R.id.buttonRegistrate)

        button.setOnClickListener{

            val email = email.editText?.text.toString()
            val password = password.editText?.text.toString()

            if(email.isEmpty() || password.isEmpty()){
                Toast.makeText(this,"თქვენ უნდა მიუთითოთ თქვენი e-mail მისამართი და პაროლი", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(this, "წარმატებით შეიქმნა!", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "შეცდომა!", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }
}