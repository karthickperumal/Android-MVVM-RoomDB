package com.studentsrecord

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.studentsrecord.view.StudentListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFrgament()
    }

    fun loadFrgament() {
        val textFragment = StudentListFragment()

        // Get the support fragment manager instance
        val manager = supportFragmentManager

        // Begin the fragment transition using support fragment manager
        val transaction = manager.beginTransaction()

        // Replace the fragment on container
        transaction.replace(R.id.fragment_container,textFragment)
        transaction.addToBackStack(null)

        // Finishing the transition
        transaction.commit()
    }
}