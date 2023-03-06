package tn.esprit.curriculumvitaev2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.adapters.EduAdapter
import tn.esprit.curriculumvitaev2.dao.EduDao
import tn.esprit.curriculumvitaev2.entity.Education
import tn.esprit.curriculumvitaev2.utils.AppDataBase


class EduFragment : Fragment() {

    private lateinit var schoolsRV: RecyclerView
    private lateinit var eduAdapter: EduAdapter
    private lateinit var appDatabase: AppDataBase
    private lateinit var schoolsList: MutableList<Education>
    private lateinit var eduDao: EduDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDataBase.getDatabase(view.context)

        eduDao = appDatabase.eduDao()

        val dbItems = eduDao.getAll()

        schoolsList = dbItems.toMutableList()

        eduAdapter = EduAdapter(schoolsList)

        schoolsRV = view.findViewById(R.id.schoolsRV)

        schoolsRV.adapter = eduAdapter

        schoolsRV.layoutManager = LinearLayoutManager(view.context)

        if (dbItems.size > schoolsList.size) {
            schoolsList.add(dbItems[schoolsList.size])
            eduAdapter.notifyItemInserted(schoolsList.size - 1)
        }
    }
}