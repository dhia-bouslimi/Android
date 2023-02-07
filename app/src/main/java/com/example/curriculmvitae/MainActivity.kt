package com.example.curriculmvitae

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.curriculmvitae.logic.clearForm
import com.example.curriculmvitae.logic.nextAction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Components
        val androidSkill = findViewById<SeekBar>(R.id.andrSkBar)
        val iosSkill = findViewById<SeekBar>(R.id.iosSkBar)
        val flutterSkill = findViewById<SeekBar>(R.id.flutterSkBar)

        val gender = findViewById<RadioGroup>(R.id.radGROUP)

        val fNameInput = findViewById<EditText>(R.id.fNameInput)
        val ageInput = findViewById<EditText>(R.id.ageInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)

        val inputs = listOf(
            fNameInput,
            ageInput,
            emailInput
        )

        val skills = listOf(
            androidSkill,
            iosSkill,
            flutterSkill
        )

        // Event listeners
       // emailInput.on(EditorInfo.IME_ACTION_DONE) { emailInput.clearFocus(); emailInput.hideKeyboard() }

        skills.forEach {
            it.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                    with(seekBar!!) {
                        progressDrawable.setTint(resources.getColor(R.color.colorSecondary))
                        thumb.setTint(resources.getColor(R.color.colorSecondary))
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                    //
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    if (seekBar.progress == 100) {
                        with(seekBar) {
                            progressDrawable.setTint(ContextCompat.getColor(context, R.color.gold))
                            thumb.setTint(ContextCompat.getColor(context, R.color.gold))
                        }
                    }
                }
            })
        }

        findViewById<Button>(R.id.resetBtn).setOnClickListener {

            clearForm(
                this,
                gender,
                inputs,
                skills
            )
        }
        findViewById<Button>(R.id.nextBtn).setOnClickListener {

            nextAction(
                this,
                inputs,
                skills
            )
        }

    }
}