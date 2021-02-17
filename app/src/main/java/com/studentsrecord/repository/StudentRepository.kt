package com.studentsrecord.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.studentsrecord.model.StudentTableModel
import com.studentsrecord.room.StudentDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class StudentRepository {

    companion object {

        var studentDatabase: StudentDatabase? = null

        var studentTableModel: LiveData<List<StudentTableModel>>? = null

        fun initializeDB(context: Context) : StudentDatabase {
            return StudentDatabase.getDataseClient(context)
        }

        fun insertData(context: Context, rollNumber: Int, name: String, fatherName: String, mobileNumber: String) {

            studentDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                val loginDetails = StudentTableModel(rollNumber, name, fatherName, mobileNumber)
                studentDatabase!!.studentDAO().InsertData(loginDetails)
            }

        }

        fun deleteStudent(rollNo: Int, context: Context) {
            studentDatabase = initializeDB(context)

            CoroutineScope(IO).launch {
                //val loginDetails = StudentTableModel(rollNumber, name, fatherName, mobileNumber)
                studentDatabase!!.studentDAO().DeleteStudent(rollNo)
            }
        }

        fun getAllStudentDetails(context: Context) : LiveData<List<StudentTableModel>>? {

            studentDatabase = initializeDB(context)

            studentTableModel = studentDatabase!!.studentDAO().getAllStudentDetails()

            return studentTableModel
        }

    }
}