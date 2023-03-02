package tn.esprit.curriculumvitaev2

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.textfield.TextInputLayout
import tn.esprit.curriculumvitaev2.entity.Experience
import tn.esprit.curriculumvitaev2.utils.*
import java.sql.Date

class AddExperienceActivity : AppCompatActivity() {

    private lateinit var addExpToolbar: Toolbar

    private lateinit var expImage: ImageView

    private lateinit var expNameTL: TextInputLayout
    private lateinit var expAddrTL: TextInputLayout
    private lateinit var expStDateTL: TextInputLayout
    private lateinit var expDateTL: TextInputLayout

    private lateinit var expName: EditText
    private lateinit var expAddr: EditText
    private lateinit var expStartDate: EditText
    private lateinit var expEndDate: EditText

    private lateinit var saveBtnEx: Button

    private val calendar: Calendar = Calendar.getInstance()

    private lateinit var appDatabase: AppDataBase

    private lateinit var uri: Uri

    private var isAccepted = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE) {
            expImage.setImageURI(data?.data)
            uri = data?.data!!
            isAccepted = true
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_experience)

        appDatabase = AppDataBase.getDatabase(this)

        addExpToolbar = findViewById(R.id.addExpToolbar)

        setSupportActionBar(addExpToolbar)

        addExpToolbar.setNavigationOnClickListener {
            finish()
        }

        expImage = findViewById(R.id.expImage)

        expImage.setOnClickListener {

            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), PICK_IMAGE_CODE
            )
        }

        expNameTL = findViewById(R.id.expNameTL)
        expAddrTL = findViewById(R.id.expAddrTL)
        expStDateTL = findViewById(R.id.expStDateTL)
        expDateTL = findViewById(R.id.expDateTL)

        expName = findViewById(R.id.expName)
        expAddr = findViewById(R.id.expAddr)
        expStartDate = findViewById(R.id.expStartDate)
        expEndDate = findViewById(R.id.expEndDate)

        val inputs = listOf(
            expName,
            expAddr,
            expStartDate,
            expEndDate
        )

        val inputLayouts = listOf(
            expNameTL,
            expAddrTL,
            expStDateTL,
            expDateTL
        )

        saveBtnEx = findViewById(R.id.saveBtnEx)

        expStartDate.onFocusChangeListener = View.OnFocusChangeListener { _, b ->

            if (b) {
                DatePickerDialog(
                    this,
                    dateListener(expStartDate, calendar),
                    calendar.get(Calendar.YEAR),
                    calendar.get(
                        Calendar.MONTH
                    ),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
                expStartDate.apply {
                    clearFocus()
                    hideKeyboard()
                }
            }

        }

        expEndDate.onFocusChangeListener = View.OnFocusChangeListener { _, b ->
            if (b) {
                DatePickerDialog(
                    this,
                    dateListener(expEndDate, calendar),
                    calendar.get(Calendar.YEAR),
                    calendar.get(
                        Calendar.MONTH
                    ),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
                expEndDate.apply {
                    clearFocus()
                    hideKeyboard()
                }
            }
        }

        saveBtnEx.setOnClickListener {

            when {

                !isAccepted -> {

                    Toast.makeText(
                        this, "Please select an image !", Toast.LENGTH_SHORT
                    ).show()
                }

                inputs.any { it.text?.isBlank()!! } -> {
                    inputLayouts.forEach {
                        if (it.editText?.text?.isBlank()!!) {
                            it.apply {
                                isErrorEnabled = true
                                when (id) {
                                    R.id.expName -> it.error =
                                        "Company name mustn't be blank !"
                                    R.id.expAddr -> it.error = "Company address mustn't be blank !"
                                    R.id.expStartDate -> it.error = "Start date mustn't be blank !"
                                    R.id.expEndDate -> it.error = "End date mustn't be blank !"
                                }
                            }
                        }
                    }
                }

                else -> {

                    validateInputs(inputLayouts)

                    appDatabase.expDao().create(
                        Experience(
                            image = uri.toString(),
                            companyName = expName.text.toString(),
                            companyAddress = expAddr.text.toString(),
                            startDate = Date.valueOf(expStartDate.text.toString()),
                            endDate = Date.valueOf(expEndDate.text.toString())
                        )
                    )
                    finish()
                }
            }
        }
    }
}