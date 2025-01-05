import {Workout} from "../types/Workout";
import { createContext, useEffect, useState } from "react";
import * as SQLite from "expo-sqlite";

const db = SQLite.openDatabaseAsync("workouts.db");

type WorkoutsContextType = {
    workouts: Workout[];
    addWorkout: (workout: Omit<Workout, "id">) => void;
    updateWorkout: (workout: Workout) => void;
    removeWorkout: (id: number) => void;
    
    };

export const WorkoutsContext = createContext<WorkoutsContextType>({
    workouts: [],
    addWorkout: () => {},
    updateWorkout: () => {},
    removeWorkout: () => {},

    });

export const WorkoutsProvider = ({ children }: any) => {
    const [workouts, setWorkouts] = useState<Workout[]>([]);

    const initializeDatabase = async () => {
        (await db).execAsync(
          `CREATE TABLE IF NOT EXISTS workouts (
              id INTEGER PRIMARY KEY AUTOINCREMENT,
              name TEXT NOT NULL,
              description TEXT NOT NULL,
              duration INTEGER NOT NULL,
              intensity TEXT NOT NULL,
              burnedCalories TEXT NOT NULL
            );`
        );


      };

    const fetchWorkouts = async () => {
        const allRows = (await db).getAllAsync("SELECT * FROM workouts");
        const workouts = (await allRows).map((row: any) => {
            return {
                id: row.id,
                name: row.name,
                description: row.description,
                duration: row.duration,
                intensity: row.intensity,
                burnedCalories: row.burnedCalories,
            }
        });
        setWorkouts(workouts);
    };

    useEffect(() => {
        initializeDatabase();
        fetchWorkouts();
    }, []);

    const addWorkout = async (workout: Omit<Workout, "id">) => {
        (await db).runAsync(
          "INSERT INTO workouts (name, description, duration, intensity, burnedCalories) VALUES (?, ?, ?, ?, ?);",
          [
            workout.name,
            workout.description,
            workout.duration,
            workout.intensity,
            workout.burnedCalories,
            
          ]
        );
        fetchWorkouts();
      };
    
      const updateWorkout = async (workout: Workout) => {
        (await db).runAsync(
          "UPDATE workouts SET name = ?, description = ?, duration = ?, intensity = ?, burnedCalories = ? WHERE id = ?;",
          [
            workout.name,
            workout.description,
            workout.duration,
            workout.intensity,
            workout.burnedCalories,
            workout.id,
          ]
        );
        fetchWorkouts();
    };
    
      const removeWorkout = async (id: number) => {
        (await db).runAsync("DELETE FROM workouts WHERE id = ?;", [id]);
        fetchWorkouts();
      };
    
      return (
        <WorkoutsContext.Provider
          value={{ workouts, addWorkout, updateWorkout, removeWorkout }}
        >
          {children}
        </WorkoutsContext.Provider>
      );

}