package com.example.cvv2.fragments
import android.net.Uri
import com.example.cvv2.entity.Education
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cvv2.R
import com.example.cvv2.adapters.EduAdapter
import java.util.*


class EduFragment : Fragment() {

    private lateinit var schoolsRV: RecyclerView
    private lateinit var eduAdapter: EduAdapter
    private lateinit var schoolsList: MutableList<Education>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val imageUri = Uri.parse("android.resource://com.example.cvv2/${R.drawable.ic_logo_cambridge}")
        val imageUri2 = Uri.parse("android.resource://com.example.cvv2/${R.drawable.ic_logo_esprit}")
        val imageUri3 = Uri.parse("android.resource://com.example.cvv2/${R.drawable.ic_logo_harvard}")
        val calendar = Calendar.getInstance()
        calendar.set(2022, Calendar.JANUARY, 1)
        val date = Date(calendar.timeInMillis)




        val  schoolsList = listOf(
          Education(
              image = imageUri.toString(),
              universityName = "Cambridge",
              universityAddress = "123 Main St.",
              startDate = date,
              endDate = date
          ),
          Education(
              image = imageUri2.toString(),
              universityName = "ESPRIT",
              universityAddress = "123 Main St.",
              startDate = date,
              endDate = date
          ),
          Education(
              image = imageUri3.toString(),
              universityName = "Harvard",
              universityAddress = "123 Main St.",
              startDate = date,
              endDate = date
          )
        )



        schoolsRV = view.findViewById(R.id.schoolsRV)


        eduAdapter = EduAdapter(schoolsList.toMutableList())

        schoolsRV.adapter = eduAdapter

        schoolsRV.layoutManager = LinearLayoutManager(view.context)


    }
}