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
import tn.esprit.curriculumvitaev2.entity.Education
import tn.esprit.curriculumvitaev2.utils.*
import java.sql.Date


class AddEducationActivity : AppCompatActivity() {

    private lateinit var addEduToolbar: Toolbar

    private lateinit var uniImage: ImageView

    private lateinit var uniNameTL: TextInputLayout
    private lateinit var uniAddrTL: TextInputLayout
    private lateinit var uniStDateTL: TextInputLayout
    private lateinit var uniEdDateTL: TextInputLayout

    private lateinit var uniName: EditText
    private lateinit var uniAddr: EditText
    private lateinit var uniStartDate: EditText
    private lateinit var uniEndDate: EditText

    private lateinit var saveBtnEd: Button

    private lateinit var appDatabase: AppDataBase

    private val calendar: Calendar = Calendar.getInstance()

    private lateinit var uri: Uri

    private var isAccepted = false

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE_CODE) {
            uniImage.setImageURI(data?.data)
            uri = data?.data!!
            isAccepted = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_education)

        appDatabase = AppDataBase.getDatabase(this)

        addEduToolbar = findViewById(R.id.addEduToolbar)

        setSupportActionBar(addEduToolbar)

        addEduToolbar.setNavigationOnClickListener {
            finish()
        }

        uniImage = findViewById(R.id.uniImage)

        uniImage.setOnClickListener {

            startActivityForResult(
                Intent(
                    Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI
                ), PICK_IMAGE_CODE
            )
        }


        uniNameTL = findViewById(R.id.uniNameTL)
        uniAddrTL = findViewById(R.id.uniAddrTL)
        uniStDateTL = findViewById(R.id.uniStDateTL)
        uniEdDateTL = findViewById(R.id.uniEdDateTL)

        uniName = findViewById(R.id.uniName)
        uniAddr = findViewById(R.id.uniAddr)
        uniStartDate = findViewById(R.id.uniStartDate)
        uniEndDate = findViewById(R.id.uniEndDate)

        saveBtnEd = findViewById(R.id.saveBtnEd)

        val inputLayouts = listOf(
            uniNameTL,
            uniAddrTL,
            uniStDateTL,
            uniEdDateTL
        )

        val inputs = listOf(
            uniName,
            uniAddr,
            uniStartDate,
            uniEndDate
        )

        uniStartDate.onFocusChangeListener = View.OnFocusChangeListener { _, b ->

            if (b) {
                DatePickerDialog(
                    this,
                    dateListener(uniStartDate, calendar),
                    calendar.get(Calendar.YEAR),
                    calendar.get(
                        Calendar.MONTH
                    ),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
                uniStartDate.apply {
                    clearFocus()
                    hideKeyboard()
                }
            }

        }

        uniEndDate.onFocusChangeListener = View.OnFocusChangeListener { _, b ->
            if (b) {
                DatePickerDialog(
                    this,
                    dateListener(uniEndDate, calendar),
                    calendar.get(Calendar.YEAR),
                    calendar.get(
                        Calendar.MONTH
                    ),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
                uniEndDate.apply {
                    clearFocus()
                    hideKeyboard()
                }
            }
        }


        saveBtnEd.setOnClickListener {

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
                                    R.id.uniName -> it.error =
                                        "University name mustn't be blank !"
                                    R.id.uniAddr -> it.error =
                                        "University address mustn't be blank !"
                                    R.id.uniStartDate -> it.error = "Start date mustn't be blank !"
                                    R.id.uniEndDate -> it.error = "End date mustn't be blank !"
                                }
                            }
                        }
                    }
                }

                else -> {

                    validateInputs(inputLayouts)

                    appDatabase.eduDao().create(
                        Education(
                            image = uri.toString(),
                            universityName = uniName.text.toString(),
                            universityAddress = uniAddr.text.toString(),
                            startDate = Date.valueOf(uniStartDate.text.toString()),
                            endDate = Date.valueOf(uniEndDate.text.toString())
                        )
                    )
                    finish()
                }
            }
        }
    }

}