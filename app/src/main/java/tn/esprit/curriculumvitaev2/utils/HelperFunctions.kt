package tn.esprit.curriculumvitaev2.utils

import android.app.DatePickerDialog
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.textfield.TextInputLayout
import java.util.*

fun navigateToFragment(
    fragContainer: Int,
    fragManager: FragmentManager,
    fragment: Fragment,
    navStack: String
) {
    fragManager.beginTransaction().replace(
        fragContainer, fragment
    ).addToBackStack(navStack).commit()
}

fun alert(context: Context, title: Int, body: Int, positiveAction: () -> Unit) {
    val builder = AlertDialog.Builder(context)
    //set title for alert dialog
    builder.setTitle(title)
    //set message for alert dialog
    builder.setMessage(body)
    builder.setIcon(android.R.drawable.ic_dialog_alert)

    //performing positive action
    builder.setPositiveButton("Yes") { dialogInterface, _ ->
        positiveAction()
        dialogInterface.dismiss()
    }

    //performing negative action
    builder.setNegativeButton("No") { dialogInterface, _ ->
        dialogInterface.dismiss()
    }

    // Create the AlertDialog
    val alertDialog: AlertDialog = builder.create()
    // Set other dialog properties
    alertDialog.setCancelable(true)
    alertDialog.show()
}

fun setDate(input: EditText, calendar: Calendar) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.FRANCE)
    input.setText(dateFormat.format(calendar.time))
}

fun dateListener(input: EditText, calendar: Calendar) =
    DatePickerDialog.OnDateSetListener { _, year, month, day ->
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        setDate(input, calendar)
    }

fun validateInputs(inputLayouts: List<TextInputLayout>) {
    inputLayouts.filter { it.isErrorEnabled }.forEach {
        it.apply {
            isErrorEnabled = false;
            error = ""
        }
    }
}