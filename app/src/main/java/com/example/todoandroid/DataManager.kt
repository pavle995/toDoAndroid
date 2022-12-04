package com.example.todoandroid

object DataManager {
    val tasks = ArrayList<Task>()

    init {
        initializeTasks()
    }

    private fun initializeTasks() {
        var t = Task(false, "wash dishes")
        tasks.add(t)

        t = Task(false, "do homework")
        tasks.add(t)

        t = Task(true, "paint the walls")
        tasks.add(t)
    }

}