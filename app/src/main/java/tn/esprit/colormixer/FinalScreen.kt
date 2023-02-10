package tn.esprit.colormixer

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess

class FinalScreen : AppCompatActivity() {

    private lateinit var resView: View
    private lateinit var resImg: ImageView
    private lateinit var honorsTxt: TextView
    private lateinit var resultTxt: TextView
    private lateinit var quitBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_final_screen)

        val isCorrect = intent.getBooleanExtra("isCorrect", false)
        val username = intent.getStringExtra("username")

        resView = findViewById(R.id.bgResult)
        resImg = findViewById(R.id.imgResult)
        honorsTxt = findViewById(R.id.honorsTxt)
        resultTxt = findViewById(R.id.resultTxt)
        quitBtn = findViewById(R.id.quitBtn)

        resView.setBackgroundResource(
            when {

                isCorrect -> R.color.success
                else -> R.color.error
            }
        )

        resImg.setImageResource(
            when {
                isCorrect -> R.drawable.check_img
                else -> R.drawable.error
            }
        )

        honorsTxt.text = when {
            isCorrect -> "Congratulations $username !"
            else -> "Sorry $username !"
        }

        resultTxt.text = when {
            isCorrect -> "Your answer is correct !"
            else -> "Your answer is wrong !"
        }

        quitBtn.setBackgroundColor(
            resources.getColor(
                when {
                    isCorrect -> R.color.success
                    else -> R.color.error
                }
            )
        )
    }
}