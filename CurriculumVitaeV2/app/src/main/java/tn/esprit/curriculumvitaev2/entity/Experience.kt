package tn.esprit.curriculumvitaev2.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity
data class Experience(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = 0,
    val image: String,
    val companyName: String,
    val companyAddress: String,
    val startDate: Date,
    val endDate: Date
)