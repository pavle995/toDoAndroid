package com.example.todoandroid

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.todoandroid.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        var undoneTasks = DataManager.tasks
        undoneTasks.removeIf { x -> x.done }

        val listTasks = findViewById<ListView>(R.id.task_list)
        listTasks.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_checked, undoneTasks)

        listTasks.setOnItemClickListener{parent, view, position, id ->
            DataManager.tasks[position].done = true
            this.recreate();
        }

        binding.fab.setOnClickListener { view ->
            var dialog = TaskFragment()
            dialog.show(supportFragmentManager, "taskDialog")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()
        val listNotes = findViewById<ListView>(R.id.task_list)
        (listNotes.adapter as ArrayAdapter<Task>).notifyDataSetChanged()
    }
}