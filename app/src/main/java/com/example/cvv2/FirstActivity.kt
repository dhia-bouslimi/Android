package com.example.cvv2

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isEmpty
import com.google.android.material.textfield.TextInputLayout
import java.io.IOException

class FirstActivity : AppCompatActivity() {

    private lateinit var Fullname: TextInputLayout
    private lateinit var Email: TextInputLayout
    private lateinit var Age: TextInputLayout
    private lateinit var RadioGroup: RadioGroup
    private lateinit var Male: RadioButton
    private lateinit var Female: RadioButton
    private lateinit var Next: Button
    private lateinit var ProfilePic: ImageView

    private val PICK_IMAGE_REQUEST = 1
    private var selectedImage: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.first)
        supportActionBar?.title = "Create your Resume"

        Fullname = findViewById(R.id.fullname)
        Email = findViewById(R.id.email)
        Age = findViewById(R.id.age)
        RadioGroup = findViewById(R.id.radioGroup)
        Male = findViewById(R.id.male)
        Female = findViewById(R.id.female)
        Next = findViewById(R.id.textButton)
        ProfilePic = findViewById(R.id.profile)

        ProfilePic.setOnClickListener {
            // Launch the image selection intent
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
        }




        Next.setOnClickListener {
            if (Fullname.editText?.text.toString().isEmpty() || Email.editText?.text.toString().isEmpty() || Age.editText?.text.toString().isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("fullname", Fullname.editText?.text.toString())
                print(Fullname.editText?.text.toString())
                intent.putExtra("email", Email.editText?.text.toString())
                intent.putExtra("age", Age.editText?.text.toString())

                if (Male.isChecked) {
                    intent.putExtra("gender", "male")
                } else {
                    intent.putExtra("gender", "female")
                }
                // Pass the selected image with the intent
                if (selectedImage != null) {
                    intent.putExtra("profile", selectedImage.toString())
                }
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val uri: Uri = data.data!!
            // Save the selected image Uri
            selectedImage = uri
            try {
                // Get the selected image as a Bitmap
                val inputStream = contentResolver.openInputStream(uri)
                val selectedImage = BitmapFactory.decodeStream(inputStream)
                // Set the Bitmap as the image for the ImageView
                ProfilePic.setImageURI(uri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
