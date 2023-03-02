package com.example.cvv2.adapters

import com.example.cvv2.entity.Education
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.cvv2.R
import com.example.cvv2.viewholders.EduViewHolder



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
        }
    }


    override fun getItemCount(): Int = dataSet.size
}