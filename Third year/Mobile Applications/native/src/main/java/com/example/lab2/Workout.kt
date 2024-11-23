package com.example.lab2

data class Workout (
    val id: Int,
    val name: String,
    val description: String,
    val duration : Int,
    val intensity: String,
    val burnedCalories: Int
)