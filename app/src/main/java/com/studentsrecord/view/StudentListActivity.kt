package com.studentsrecord.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.studentsrecord.R
import com.studentsrecord.model.StudentTableModel
import com.studentsrecord.utils.Constant
import com.studentsrecord.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.students_list_fragment.*

class StudentListActivity: AppCompatActivity() {

    lateinit var studentsViewModel: StudentsViewModel
    lateinit var studentListAdapter: StudentListAdapter
    var studentList: List<StudentTableModel>? = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.students_list_fragment)
        studentsViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
        studentListAdapter = StudentListAdapter(ArrayList(), this, studentsViewModel)
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    private fun getAllStudents() {
        pb_loading?.visibility = View.VISIBLE
        studentsViewModel.getAllStudentDetails(this)!!.observe(this, Observer {

            if (it.isNullOrEmpty()) {
                tv_no_data?.visibility = View.VISIBLE
                pb_loading?.visibility = View.GONE
            }
            else {
                pb_loading?.visibility = View.GONE
                studentList = it
                studentListAdapter = StudentListAdapter(
                    it as ArrayList<StudentTableModel>,
                    this,
                    studentsViewModel
                )
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val actionAdd = menu?.findItem(R.id.action_add)?.actionView
        actionAdd?.setOnClickListener {

        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_add -> {
                Toast.makeText(this, "Add clicked", Toast.LENGTH_LONG).show()
                val intent = Intent(this, StudentDetailActivity::class.java)
                intent.putExtra(Constant.ADD_NEW, true)
                startActivity(intent)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}