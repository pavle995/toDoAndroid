package com.example.todoandroid

class Task(var done: Boolean = false, var name: String) {
    override fun toString(): String {
        return name
    }
}