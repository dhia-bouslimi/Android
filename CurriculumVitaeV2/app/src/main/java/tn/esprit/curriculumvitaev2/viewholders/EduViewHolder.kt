package tn.esprit.curriculumvitaev2.viewholders

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.R

class EduViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var schoolImage: ImageView? = null
    var stDate: TextView? = null
    var fDate: TextView? = null
    var cName: TextView? = null
    var cAddr: TextView? = null
    var btnDel: ImageView? = null

    init {
        schoolImage = itemView.findViewById(R.id.schoolImage)
        stDate = itemView.findViewById(R.id.stDate)
        fDate = itemView.findViewById(R.id.fDate)
        cName = itemView.findViewById(R.id.cName)
        cAddr = itemView.findViewById(R.id.cAddr)
        btnDel = itemView.findViewById(R.id.delEdu)
    }
}