package com.example.cvv2.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvv2.R
import com.example.cvv2.adapters.ExpAdapter
import com.example.cvv2.entity.Experience
import java.util.*


class ExpFragment : Fragment() {

    private lateinit var companiesRV: RecyclerView
    private lateinit var expAdapter: ExpAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageUri = Uri.parse("android.resource://com.example.cvv2/${R.drawable.ic_logo_facebook}")
        val imageUri2 = Uri.parse("android.resource://com.example.cvv2/${R.drawable.ic_logo_google}")
        val imageUri3 = Uri.parse("android.resource://com.example.cvv2/${R.drawable.ic_logo_amazon}")
        val calendar = Calendar.getInstance()
        calendar.set(2022, Calendar.JANUARY, 1)
        val date = Date(calendar.timeInMillis)


        val companiesList = listOf(

           Experience(
               image = imageUri.toString(),
               companyName = "Facebook",
               companyAddress = "Lorem",
               startDate = "2022/01/02",
               endDate = "2022/01/02",
           ),Experience(
                image = imageUri2.toString(),
                companyName = "Google",
                companyAddress = "Lorem",
                startDate = "2022/01/02",
                endDate = "2022/01/02",
            ),Experience(
                image = imageUri3.toString(),
                companyName = "Amazon",
                companyAddress = "Lorem",
                startDate = "2022/01/02",
                endDate = "2022/01/02",
            )
        )

        companiesRV = view.findViewById(R.id.companiesRV)

        expAdapter = ExpAdapter(companiesList.toMutableList())

        companiesRV.adapter = expAdapter

        companiesRV.layoutManager = LinearLayoutManager(view.context)


    }
}