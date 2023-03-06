package tn.esprit.curriculumvitaev2

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.util.Patterns
import android.view.inputmethod.EditorInfo.IME_ACTION_DONE
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.curriculumvitaev2.utils.*


class MainActivity : AppCompatActivity() {

    private lateinit var fNameTextLayout: TextInputLayout
    private lateinit var emailTextLayout: TextInputLayout
    private lateinit var ageTextLayout: TextInputLayout
    private lateinit var imageBorder: ImageView
    private lateinit var profileImage: ImageView

    private lateinit var radioGroup: RadioGroup
    private lateinit var checkedRadioButton: RadioButton
    private lateinit var nextBtn: Button

    private lateinit var layoutTitle: TextView

    private val cvObject = CvObject()

    private lateinit var sharedPrefs: SharedPreferences

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE) {
            profileImage.setImageURI(data?.data)
            cvObject.imgURI = data?.data
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            PERMS_REQUEST_CODE -> {

                if (grantResults.isNotEmpty()
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    println("GRANTED")
                    sharedPrefs.edit().putBoolean(IS_GRANTED_READ_IMAGES, true).apply()
                }
            }

            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPrefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            PERMS_REQUEST_CODE
        );

        fNameTextLayout = findViewById(R.id.fullNameTxtInput)
        emailTextLayout = findViewById(R.id.emailTxtInput)
        ageTextLayout = findViewById(R.id.ageTxtInput)

        radioGroup = findViewById(R.id.radioGroup)
        nextBtn = findViewById(R.id.nxtBtn)
        imageBorder = findViewById(R.id.borderImg)
        layoutTitle = findViewById(R.id.layoutTitle)

        profileImage = findViewById(R.id.profileImage)


        if (sharedPrefs.getBoolean(IS_REMEMBERED, false)) {
            startActivity(Intent(this, ResumePage::class.java))
        }

        supportActionBar?.title = resources.getString(R.string.title1)

        val inputLayouts = mutableListOf(
            fNameTextLayout, emailTextLayout, ageTextLayout
        )

        val inputs = inputLayouts.map { t -> t.editText }


        val ageInput = inputs.first { it?.id == R.id.ageInput }
        val emailInput = inputs.first { it?.id == R.id.emailInput }

        // Event listeners
        ageInput?.on(IME_ACTION_DONE) {
            ageInput.apply {
                clearFocus()
                hideKeyboard()
            }
        }

        profileImage.setOnClickListener {

            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), PICK_IMAGE_CODE
            )
        }


        nextBtn.setOnClickListener {
            val checkedID: Int = radioGroup.checkedRadioButtonId

            when {

                cvObject.imgURI == null -> {

                    Toast.makeText(
                        this, "Please select an image !", Toast.LENGTH_SHORT
                    ).show()
                }

                checkedID == -1 -> {
                    imageBorder.setImageResource(R.drawable.background_border_red)
                    layoutTitle.setTextColor(resources.getColor(R.color.errorRed))
                }

                !Patterns.EMAIL_ADDRESS.matcher(emailInput!!.text.toString()).matches() -> {
                    emailTextLayout.apply {
                        isErrorEnabled = true
                        error = "Invalid email !"
                    }
                }

                inputs.any { it?.text?.isBlank()!! } -> {
                    inputLayouts.forEach {
                        if (it.editText?.text?.isBlank()!!) {
                            it.apply {
                                isErrorEnabled = true
                                when (id) {
                                    R.id.fullNameTxtInput -> it.error =
                                        "Full name mustn't be blank !"
                                    R.id.emailTxtInput -> it.error = "Email name mustn't be blank !"
                                    R.id.ageTxtInput -> it.error = "Age mustn't be blank !"
                                }
                            }
                        }
                    }
                }

                else -> {
                    inputLayouts.filter { it.isErrorEnabled }.forEach {
                        it.apply {
                            isErrorEnabled = false;
                            error = ""
                        }
                    }
                    checkedRadioButton = findViewById(checkedID)
                    imageBorder.setImageResource(R.drawable.background_border)
                    layoutTitle.setTextColor(resources.getColor(R.color.colorPrimaryDark))


                    cvObject.fullName = fNameTextLayout.editText?.text.toString()
                    cvObject.email = emailTextLayout.editText?.text.toString()
                    cvObject.age = Integer.parseInt(ageTextLayout.editText?.text.toString())
                    cvObject.gender = checkedRadioButton.text.toString()

                    Intent(this, FormPart2::class.java).let { i ->
                        i.putExtra(INTENT_VALUE_NAME, cvObject)
                        println("CvObject: [MAIN ACT] $cvObject")
                        startActivity(i)
                    }
                }
            }
        }

    }
}