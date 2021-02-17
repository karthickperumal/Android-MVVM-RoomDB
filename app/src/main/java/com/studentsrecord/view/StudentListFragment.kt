package com.studentsrecord.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.studentsrecord.R
import com.studentsrecord.model.StudentTableModel
import com.studentsrecord.utils.Constant.ADD_NEW
import com.studentsrecord.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.students_list_fragment.*

class StudentListFragment: Fragment() {

    lateinit var studentsViewModel: StudentsViewModel
    var studentListAdapter: StudentListAdapter? = null
    var studentList: List<StudentTableModel>? = arrayListOf()

    companion object {

        fun newInstance(): StudentListFragment {
            return StudentListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        studentsViewModel = ViewModelProvider(this).get(StudentsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.students_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //studentListAdapter = StudentListAdapter(ArrayList(), this.requireContext())
        //getAllStudents()
    }

    override fun onResume() {
        super.onResume()
        getAllStudents()
    }

    private fun getAllStudents() {
        pb_loading.visibility = View.VISIBLE
        studentsViewModel.getAllStudentDetails(this.requireContext())!!.observe(this, Observer {

            if (it.isNullOrEmpty()) {
                tv_no_data.visibility = View.VISIBLE
                pb_loading.visibility = View.GONE
            }
            else {
                recycler_view.visibility = View.VISIBLE
                pb_loading.visibility = View.GONE
                studentList = it
                recycler_view.layoutManager = LinearLayoutManager(this.requireContext())
                recycler_view.setHasFixedSize(true)
                recycler_view.adapter = StudentListAdapter(it as ArrayList<StudentTableModel>, this.requireContext(), studentsViewModel)
            }
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        val actionAdd = menu.findItem(R.id.action_add).actionView
        actionAdd?.setOnClickListener {

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId){
            R.id.action_add -> {
                val intent = Intent(this.requireContext(), StudentDetailActivity::class.java)
                intent.putExtra(ADD_NEW, true)
                this.requireContext().startActivity(intent)
                true
            } else -> super.onOptionsItemSelected(item)
        }
    }
}