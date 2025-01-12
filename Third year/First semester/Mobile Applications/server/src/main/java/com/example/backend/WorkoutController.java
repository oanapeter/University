package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@CrossOrigin("*")
public class WorkoutController {
    @Autowired
    private WorkoutRepository workoutRepository;

    @GetMapping
    public List<Workout> getWorkouts() {
        return workoutRepository.findAll();
    }

    @GetMapping("/{id}")
    public Workout getWorkout(Long id) {
        return workoutRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Workout createWorkout(@RequestBody Workout workout) {
        return workoutRepository.save(workout);
    }


    @PutMapping("/{id}")
    public Workout updateWorkout(@PathVariable Long id, @RequestBody Workout workoutDetails) {
        Workout workout = workoutRepository.findById(id).orElseThrow(() -> new RuntimeException("Workout not found"));

        workout.setName(workoutDetails.getName());
        workout.setDescription(workoutDetails.getDescription());
        workout.setDuration(workoutDetails.getDuration());
        workout.setIntensity(workoutDetails.getIntensity());
        workout.setBurnedCalories(workoutDetails.getBurnedCalories());

        return workoutRepository.save(workout);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWorkout(@PathVariable Long id) {
        workoutRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
