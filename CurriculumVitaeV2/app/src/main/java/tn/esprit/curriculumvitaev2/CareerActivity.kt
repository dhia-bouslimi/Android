package tn.esprit.curriculumvitaev2

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentContainerView
import tn.esprit.curriculumvitaev2.fragments.EduFragment
import tn.esprit.curriculumvitaev2.fragments.ExpFragment
import tn.esprit.curriculumvitaev2.utils.navigateToFragment

class CareerActivity : AppCompatActivity() {

    private lateinit var btnExp: Button
    private lateinit var btnEdu: Button
    private lateinit var careerToolbar: Toolbar
    private lateinit var careerContainer: FragmentContainerView

    private lateinit var expFrag: ExpFragment

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.career_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.add_exp_action -> {
            startActivity(Intent(this, AddExperienceActivity::class.java))
            true
        }

        R.id.add_edu_action -> {
            startActivity(Intent(this, AddEducationActivity::class.java))
            true
        }

        else -> super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_career)

        btnExp = findViewById(R.id.btnExp)
        btnEdu = findViewById(R.id.btnEdu)
        careerToolbar = findViewById(R.id.careerToolBar)
        careerContainer = findViewById(R.id.careerContainer)

        setSupportActionBar(careerToolbar)

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

        careerToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}