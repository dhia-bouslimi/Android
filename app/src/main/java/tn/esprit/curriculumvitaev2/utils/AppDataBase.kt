package tn.esprit.curriculumvitaev2.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tn.esprit.curriculumvitaev2.dao.EduDao
import tn.esprit.curriculumvitaev2.dao.ExpDao
import tn.esprit.curriculumvitaev2.entity.Education
import tn.esprit.curriculumvitaev2.entity.Experience

@Database(entities = [Experience::class, Education::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun expDao(): ExpDao
    abstract fun eduDao(): EduDao

    companion object {
        @Volatile
        private var instance: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,
                        "cv-db"
                    )
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return instance!!
        }
    }
}


