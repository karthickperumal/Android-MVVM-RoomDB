package com.studentsrecord.view

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studentsrecord.R
import com.studentsrecord.model.StudentTableModel
import com.studentsrecord.utils.Constant
import com.studentsrecord.viewmodel.StudentsViewModel
import kotlinx.android.synthetic.main.item_students.view.*


class StudentListAdapter(
    val items: ArrayList<StudentTableModel>,
    val context: Context,
    val studentsViewModel: StudentsViewModel
) : RecyclerView.Adapter<ViewHolder>() {
    // Gets the number of students in the list
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_students, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvName?.text = items.get(position).studentName
        holder?.tvRoll?.text = items.get(position).rollNumber.toString()
        holder?.tvFather?.text = items.get(position).studentFatherName
        holder?.tvMobile?.text = items.get(position).mobileNumber

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, StudentDetailActivity::class.java)
            intent.putExtra("name", items[position].studentName)
            intent.putExtra("rollNo", items[position].rollNumber)
            intent.putExtra("fatherName", items[position].studentFatherName)
            intent.putExtra("MobileNo", items[position].mobileNumber)
            intent.putExtra(Constant.ADD_NEW, false)
            holder.itemView.context.startActivity(intent)
        }

        holder.itemView.setOnLongClickListener {
            items.remove(items[position])
            showDeleteDialog()
            true
        }
    }

    private fun deleteStudent() {
        notifyDataSetChanged()
    }

    private fun showDeleteDialog() {
        // create a dialog with AlertDialog builder
        // create a dialog with AlertDialog builder
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Student Record")
        builder.setMessage("Are you sure want to delete.?")

        val positiveText: String = "Yes"
        builder.setPositiveButton(positiveText
        ) { dialog, which -> // dismiss alert dialog, update preferences with game score and restart play fragment
            deleteStudent()
            dialog.dismiss()
        }

        val negativeText: String = "No"
        builder.setNegativeButton(negativeText
        ) { dialog, which -> // dismiss dialog, start counter again
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
// display dialog
// display dialog
        dialog.show()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvName = view.tv_student_name
    val tvRoll = view.tv_roll_noe
    val tvFather = view.tv_father_name
    val tvMobile = view.tv_mobile_no
}


