package com.example.backend.WebSocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class WorkoutNotificationService {
    @Autowired
    private SimpMessagingTemplate template;

    public void notifyWorkoutChange(WorkoutMessage workoutMessage) {
        template.convertAndSend("/topic/workouts", workoutMessage);
    }
}
