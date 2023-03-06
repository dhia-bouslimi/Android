package tn.esprit.curriculumvitaev2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.entity.Experience
import tn.esprit.curriculumvitaev2.utils.AppDataBase
import tn.esprit.curriculumvitaev2.utils.alert
import tn.esprit.curriculumvitaev2.viewholders.ExpViewHolder

class ExpAdapter(private val dataSet: MutableList<Experience>) : RecyclerView.Adapter<ExpViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpViewHolder =
        ExpViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.layout_exp_card, parent, false)
        )

    override fun onBindViewHolder(holder: ExpViewHolder, position: Int) {

        holder.apply {
            companyName!!.text = dataSet[position].companyName
            companyAddress!!.text = dataSet[position].companyAddress
            startDate!!.text = dataSet[position].startDate.toString()
            endDate!!.text = dataSet[position].endDate.toString()
            companyImage!!.setImageURI(dataSet[position].image.toUri())

            btnDel!!.setOnClickListener {

                alert(it.context, R.string.del_edu_title, R.string.edu_prompt) {

                    AppDataBase.getDatabase(it.context).expDao()
                        .deleteOne(dataSet[position])
                    dataSet.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = dataSet.size
}