package tn.esprit.curriculumvitaev2.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import tn.esprit.curriculumvitaev2.entity.Experience

@Dao
interface ExpDao : GenericDao<Experience> {

    @Query("SELECT * FROM Experience")
    override fun getAll(): List<Experience>

    @Insert
    override fun create(t: Experience)

    @Query("SELECT * FROM Experience WHERE id = :id")
    override fun getOne(id: Int): Experience

    @Delete
    override fun deleteOne(t: Experience)
}