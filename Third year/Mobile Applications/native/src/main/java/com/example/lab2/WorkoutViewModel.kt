package com.example.lab2

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WorkoutViewModel : ViewModel() {
    private val repository = WorkoutRepository()

    var workouts = mutableStateListOf<Workout>()
        private set

    init {
        workouts.addAll(repository.getWorkouts())
    }

    fun addWorkout(workout: Workout) {
        repository.addWorkout(workout)
        workouts.add(workout)
    }

    fun removeWorkoutById(id: Int) {
        repository.removeWorkoutById(id)
        workouts.removeIf { it.id == id }
    }

    fun updateWorkout(updatedWorkout: Workout) {
        repository.updateWorkout(updatedWorkout)
        val index = workouts.indexOfFirst { it.id == updatedWorkout.id }
        if (index != -1) {
            workouts[index] = updatedWorkout
        }
    }

    fun getNextId(): Int {
        return repository.getNextId()
    }
}


