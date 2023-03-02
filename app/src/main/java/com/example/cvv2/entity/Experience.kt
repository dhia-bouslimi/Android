package com.example.cvv2.entity
import android.os.Parcelable
import androidx.versionedparcelable.VersionedParcelize
import java.util.*



data class Experience(


    val image: String,
    val companyName: String,
    val companyAddress: String,
    val startDate: String,
    val endDate: String
)