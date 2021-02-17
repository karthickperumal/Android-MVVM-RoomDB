package com.studentsrecord.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Students")
data class StudentTableModel (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "RollNo")
    var rollNumber: Int? = 0,
    @ColumnInfo(name = "StudentName")
    var studentName: String? = "",
    @ColumnInfo(name = "StudentFatherName")
    var studentFatherName: String? = "",
    @ColumnInfo(name = "MobileNumber")
    var mobileNumber: String? = ""
)