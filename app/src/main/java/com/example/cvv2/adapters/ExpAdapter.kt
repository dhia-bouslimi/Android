package com.example.cvv2.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.cvv2.R
import com.example.cvv2.entity.Experience
import com.example.cvv2.viewholders.ExpViewHolder


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

        }
    }

    override fun getItemCount(): Int = dataSet.size
}