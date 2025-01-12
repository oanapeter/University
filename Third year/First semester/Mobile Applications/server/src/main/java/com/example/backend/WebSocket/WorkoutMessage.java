package com.example.backend.WebSocket;

import com.example.backend.Workout;

public class WorkoutMessage {
    private String operation;
    private Workout workout;
    private Long id;

    public WorkoutMessage(){

    }

    public WorkoutMessage(String operation, Workout workout, Long id) {
        this.operation = operation;
        this.workout = workout;
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public Workout getWorkout() {
        return workout;
    }
}
