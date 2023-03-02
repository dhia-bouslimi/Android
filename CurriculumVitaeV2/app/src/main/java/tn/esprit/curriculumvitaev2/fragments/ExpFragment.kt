package tn.esprit.curriculumvitaev2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.curriculumvitaev2.R
import tn.esprit.curriculumvitaev2.adapters.ExpAdapter
import tn.esprit.curriculumvitaev2.dao.ExpDao
import tn.esprit.curriculumvitaev2.entity.Experience
import tn.esprit.curriculumvitaev2.utils.AppDataBase


class ExpFragment : Fragment() {

    private lateinit var companiesRV: RecyclerView
    private lateinit var expAdapter: ExpAdapter
    private lateinit var appDatabase: AppDataBase
    private lateinit var companiesList: MutableList<Experience>
    private lateinit var expDao: ExpDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        appDatabase = AppDataBase.getDatabase(view.context)

        expDao = appDatabase.expDao()

        val dbItems = expDao.getAll()

        companiesList = dbItems.toMutableList()

        companiesRV = view.findViewById(R.id.companiesRV)

        expAdapter = ExpAdapter(companiesList)

        companiesRV.adapter = expAdapter

        companiesRV.layoutManager = LinearLayoutManager(view.context)

        if (dbItems.size > companiesList.size) {
            companiesList.add(dbItems[companiesList.size])
            expAdapter.notifyItemInserted(companiesList.size - 1)
        }
    }
}