package tn.esprit.curriculumvitaev2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.entity.Education
import tn.esprit.curriculumvitaev2.utils.AppDataBase
import tn.esprit.curriculumvitaev2.utils.alert
import tn.esprit.curriculumvitaev2.viewholders.EduViewHolder

class EduAdapter(private val dataSet: MutableList<Education>) :
    RecyclerView.Adapter<EduViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EduViewHolder =
        EduViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_edu_card, parent, false)
        )

    override fun onBindViewHolder(holder: EduViewHolder, position: Int) {
        holder.apply {
            cName!!.text = dataSet[position].universityName
            cAddr!!.text = dataSet[position].universityAddress
            stDate!!.text = dataSet[position].startDate.toString()
            fDate!!.text = dataSet[position].endDate.toString()
            schoolImage!!.setImageURI(dataSet[position].image.toUri())

            btnDel!!.setOnClickListener {

                alert(it.context, R.string.del_edu_title, R.string.edu_prompt) {

                    AppDataBase.getDatabase(it.context).eduDao()
                        .deleteOne(dataSet[position])
                    dataSet.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }


    override fun getItemCount(): Int = dataSet.size
}