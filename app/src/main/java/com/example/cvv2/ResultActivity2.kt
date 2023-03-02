package com.example.cvv2

import HobbiesFragment
import SkillsFragment
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import com.example.cvv2.fragments.BasicInfoFragment
import com.example.cvv2.fragments.LanguageFragment



class ResultActivity2 : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var profileImage: ImageView
    private lateinit var skillsBtn: Button
    private lateinit var hobbiesBtn: Button
    private lateinit var langBtn: Button
    private lateinit var careerBtn: Button
    private lateinit var toolbar: Toolbar
    private lateinit var fragContainer: FragmentContainerView
    private lateinit var basicInfoFragment: BasicInfoFragment





    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


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



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.result2)

        basicInfoFragment = BasicInfoFragment.new(
            intent.getStringExtra("fullname").toString(), intent.getStringExtra("gender").toString(),intent.getStringExtra("age"),intent.getStringExtra("email").toString()
        )
        toolbar = findViewById(R.id.toolBar)
        val button = toolbar.menu.findItem(R.id.info)
        button.setOnMenuItemClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, basicInfoFragment, "nav")
            true
        }




        name = findViewById(R.id.nameText)
        email = findViewById(R.id.emailText)

        name.text = intent.getStringExtra("fullname")
        email.text = intent.getStringExtra("email")


        profileImage = findViewById(R.id.profilePic)
        fragContainer = findViewById(R.id.fragContainer)

        skillsBtn = findViewById(R.id.skillsBtn)
        hobbiesBtn = findViewById(R.id.hobbiesBtn)
        langBtn = findViewById(R.id.langBtn)
        careerBtn = findViewById(R.id.careerBtn)

        profileImage.setImageURI(
            intent.getStringExtra("profile")?.toUri()
        )

        supportActionBar?.hide()




        val skillsFragment = SkillsFragment.new(
            androidP = intent.getFloatExtra("android", 0.0F),
            flutterP = intent.getFloatExtra("flutter",0.0F),
            iosP = intent.getFloatExtra("ios",0.0F),

        )

        val hobbiesFragment = HobbiesFragment.new(
            isMusic = intent.getBooleanExtra("music",false),
            isGames = intent.getBooleanExtra("games",false),
            isSport = intent.getBooleanExtra("sports",false)
        )

        val languageFragment = LanguageFragment.new(
            isArabic = intent.getBooleanExtra("arabic",false),
            isEnglish = intent.getBooleanExtra("english",false),
            isFrench = intent.getBooleanExtra("french",false)
        )

        supportFragmentManager.beginTransaction().add(
            R.id.fragContainer, skillsFragment
        ).commit()


        skillsBtn.setOnClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, skillsFragment, "nav")
        }

        hobbiesBtn.setOnClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, hobbiesFragment, "nav")
        }

        langBtn.setOnClickListener {
            navigateToFragment(R.id.fragContainer, supportFragmentManager, languageFragment, "nav")
        }

        careerBtn.setOnClickListener {
            startActivity(Intent(this, CareerActivity::class.java))
        }


    }
}
