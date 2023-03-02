package com.example.cvv2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.example.cvv2.R
import com.example.cvv2.fragments.EduFragment
import com.example.cvv2.fragments.ExpFragment


class CareerActivity : AppCompatActivity() {


    private lateinit var btnExp: Button
    private lateinit var btnEdu: Button

    private lateinit var careerContainer: FragmentContainerView

    private lateinit var expFrag: ExpFragment


    private fun navigateToFragment(
        fragContainer: Int,
        fragManager: FragmentManager,
        fragment: Fragment,
        navStack: String
    ) {
        fragManager.beginTransaction().replace(
            fragContainer, fragment
        ).addToBackStack(navStack).commit()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career)
        title = "My Career"
        btnExp = findViewById(R.id.btnExp)
        btnEdu = findViewById(R.id.btnEdu)

        careerContainer = findViewById(R.id.careerContainer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        expFrag = ExpFragment()

        supportFragmentManager.beginTransaction().add(
            R.id.careerContainer, expFrag
        ).commit()

        btnExp.setOnClickListener {
            navigateToFragment(R.id.careerContainer, supportFragmentManager, expFrag, "careerNav")
        }

        btnEdu.setOnClickListener {
            navigateToFragment(R.id.careerContainer, supportFragmentManager, EduFragment(), "careerNav")
        }


    }
}