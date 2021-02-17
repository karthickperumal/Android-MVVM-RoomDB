package com.studentsrecord.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.studentsrecord.model.StudentTableModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(studentTableModel: StudentTableModel)

    @Query("DELETE FROM Students WHERE RollNo = :rollNo")
    suspend fun DeleteStudent(rollNo: Int)

    @Query("SELECT * FROM Students")
    fun getAllStudentDetails() : LiveData<List<StudentTableModel>>

}