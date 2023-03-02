package com.example.cvv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textview.MaterialTextView


class ResultActivity : AppCompatActivity() {

    private lateinit var fullname : MaterialTextView
    private lateinit var email : MaterialTextView
    private lateinit var age : MaterialTextView
    private lateinit var gender : MaterialTextView
    private lateinit var android : MaterialTextView
    private lateinit var ios : MaterialTextView
    private lateinit var flutter : MaterialTextView
    private lateinit var language : MaterialTextView
    private lateinit var hobbies : MaterialTextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.result)

        fullname = findViewById(R.id.fullname)
        fullname.text = "Fullname :" + intent.getStringExtra("fullname").toString()

        email = findViewById(R.id.email)
        email.text = "Email :" + intent.getStringExtra("email").toString()

        age = findViewById(R.id.age)
        age.text = "Age :" + intent.getStringExtra("age").toString()

        gender = findViewById(R.id.gender)
        gender.text = "Gender : " + intent.getStringExtra("gender").toString()

        android = findViewById(R.id.android)
        android.text = "Android : " + intent.getStringExtra("android").toString()

        ios = findViewById(R.id.ios)
        ios.text = "iOS : " + intent.getStringExtra("ios").toString()

        flutter = findViewById(R.id.flutter)
        flutter.text = "Flutter : " + intent.getStringExtra("flutter").toString()

        language = findViewById(R.id.lang)
        language.text = "Languages : " + intent.getStringExtra("languages").toString()

        hobbies = findViewById(R.id.hobbies)
        hobbies.text = "Hobbies : " + intent.getStringExtra("hobbies").toString()




    }
}