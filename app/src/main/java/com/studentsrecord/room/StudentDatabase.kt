package com.studentsrecord.room

import android.content.Context
import androidx.room.*
import com.studentsrecord.model.StudentTableModel

@Database(entities = arrayOf(StudentTableModel::class), version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase() {

    abstract fun studentDAO() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: StudentDatabase? = null

        fun getDataseClient(context: Context) : StudentDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, StudentDatabase::class.java, "STUDENT_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}