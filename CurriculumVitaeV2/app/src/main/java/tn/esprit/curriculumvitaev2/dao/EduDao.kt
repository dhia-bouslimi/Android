package tn.esprit.curriculumvitaev2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import tn.esprit.curriculumvitaev2.entity.Education

@Dao
interface EduDao : GenericDao<Education> {

    @Query("SELECT * FROM Education")
    override fun getAll(): List<Education>

    @Insert
    override fun create(t: Education)

    @Query("SELECT * FROM Education WHERE id = :id")
    override fun getOne(id: Int): Education

    @Delete
    override fun deleteOne(t: Education)
}