package tn.esprit.curriculumvitaev2.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.R

class ExpViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var companyImage: ImageView? = null
    var companyName: TextView? = null
    var companyAddress: TextView? = null
    var startDate: TextView? = null
    var endDate: TextView? = null
    var btnDel: ImageView? = null


    init {
        companyImage = itemView.findViewById(R.id.companyImage)
        companyName = itemView.findViewById(R.id.companyName)
        companyAddress = itemView.findViewById(R.id.companyAddress)
        startDate = itemView.findViewById(R.id.startDate)
        endDate = itemView.findViewById(R.id.endDate)
        btnDel = itemView.findViewById(R.id.delExp)

    }

}