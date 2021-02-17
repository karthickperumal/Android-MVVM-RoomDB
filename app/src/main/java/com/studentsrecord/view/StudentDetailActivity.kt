package com.studentsrecord.view

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.studentsrecord.R
import com.studentsrecord.utils.Constant.ADD_NEW
import com.studentsrecord.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.student_details.*

class StudentDetailActivity: AppCompatActivity() {

    lateinit var studentsViewModel: StudentsViewModel

    var strStudentName =""
    var strRollNo  = ""
    var strFatherName = ""
    var strMobileNo = ""
    var isAddNew = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.student_details)

        studentsViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)

        intent?.let {
            isAddNew = it.getBooleanExtra(ADD_NEW, false)
            strStudentName = it.getStringExtra("name") ?: ""
            val rollNo = it.getIntExtra("rollNo", 0)
            strRollNo = rollNo.toString()
            strFatherName = it.getStringExtra("fatherName") ?: ""
            strMobileNo = it.getStringExtra("MobileNo") ?: ""
        }

        if(isAddNew) {
            txtStudentName.setText("")
            txtRollNo.setText("")
            txtFatherName.setText("")
            txtMobileNo.setText("")
            btnAddStudent.text = "Add Student"
        } else {
            txtStudentName.setText(strStudentName)
            txtRollNo.setText(strRollNo)
            txtFatherName.setText(strFatherName)
            txtMobileNo.setText(strMobileNo)
            btnAddStudent.text = "Update Student"
        }

        btnAddStudent.setOnClickListener {

            strStudentName = txtStudentName.text.toString().trim()
            strRollNo = txtRollNo.text.toString()
            strFatherName = txtFatherName.text.toString().trim()
            strMobileNo = txtMobileNo.text.toString().trim()

            if (strStudentName.isEmpty()) {
                txtStudentName.error = "Please enter the student name"
            }
            else if (strRollNo.isEmpty()) {
                txtRollNo.error = "Please enter roll no"
            }
            else if (strFatherName.isEmpty()) {
                txtFatherName.error = "Please enter father name"
            }
            else if (strMobileNo.isEmpty()) {
                txtMobileNo.error = "Please enter mobile no"
            }
            else {
                studentsViewModel.insertData(this, strRollNo.toInt(), strStudentName, strFatherName, strMobileNo)
                Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}