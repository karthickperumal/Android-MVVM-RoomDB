package com.studentsrecord.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.studentsrecord.model.StudentTableModel
import com.studentsrecord.repository.StudentRepository

class StudentsViewModel : ViewModel() {

    var liveDataLogin: LiveData<List<StudentTableModel>>? = null

    fun insertData(context: Context, rollNo: Int, name: String, fatherName: String, mobileNo: String) {
        StudentRepository.insertData(context, rollNo, name, fatherName, mobileNo)
    }

    fun getAllStudentDetails(context: Context) : LiveData<List<StudentTableModel>>? {
        liveDataLogin = StudentRepository.getAllStudentDetails(context)
        return liveDataLogin
    }

    fun deleteStudentByRollNo(rollNo: Int, context: Context){
        StudentRepository.deleteStudent(rollNo, context)
    }

}