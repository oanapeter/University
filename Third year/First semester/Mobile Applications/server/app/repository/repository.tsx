import { Workout } from "../types/Workout";
import { createContext, useEffect, useState } from "react";
import axios from "axios";
import { Stomp } from "@stomp/stompjs";
import SockJS from "sockjs-client";
import AsyncStorage from '@react-native-async-storage/async-storage';
import { Alert } from 'react-native';

const BASE_URL = "http://10.0.2.2:8080/api/workouts";
const WS_URL = "http://10.0.2.2:8080/ws";

let stompClient: any;

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

const WorkoutsProvider = ({ children }: any) => {
  const [workouts, setWorkouts] = useState<Workout[]>([]);

  useEffect(() => {
    connectWebSocket(handleWorkoutMessage);
    fetchWorkouts();
    return () => disconnectWebSocket();
  }, []);

  useEffect(() => {
    const interval = setInterval(() => {
      console.log("Checking WebSocket connection...");
      checkWebSocketConnection(); // Check connection every 5 seconds
    }, 5000);

    return () => clearInterval(interval); // Clear interval on component unmount
  }, []);

  // Fetch workouts from the server
  const fetchWorkouts = async () => {
    try {
      const response = await axios.get(BASE_URL);
      setWorkouts(response.data);
    } catch (error) {
      console.error("Failed to fetch workouts:", error);
    }
  };

  // Add a workout (offline support)
  const addWorkout = async (workout: Omit<Workout, "id">) => {
    if (stompClient && stompClient.connected) {
      await addWorkoutToServer(workout);
    } else {
      console.warn("No connection to server. Saving workout offline...");
      await saveWorkoutOffline(workout);
    }
  };

  // Save workout to server
  const addWorkoutToServer = async (workout: Omit<Workout, "id">) => {
    try {
      const response = await axios.post(BASE_URL, workout);
      setWorkouts((prevWorkouts) => [...prevWorkouts, response.data]);
    } catch (error) {
      console.error("Failed to add workout to server:", error);
    }
  };

  // Sync offline workouts to server
  const syncOfflineWorkouts = async () => {
    try {
      const offlineWorkouts = await AsyncStorage.getItem('offlineWorkouts');
      if (offlineWorkouts) {
        const workoutsArray = JSON.parse(offlineWorkouts);
        for (const workout of workoutsArray) {
          await addWorkoutToServer(workout);
        }
        await AsyncStorage.removeItem('offlineWorkouts');
        console.log("Offline workouts synced to server.");
        fetchWorkouts(); // Refresh workout list after sync
      }
    } catch (error) {
      console.error("Error syncing offline workouts:", error);
    }
  };

  // Save workout locally when offline
  const saveWorkoutOffline = async (workout: Omit<Workout, "id">) => {
    try {
      const offlineWorkouts = await AsyncStorage.getItem("offlineWorkouts");
      const workoutsArray = offlineWorkouts ? JSON.parse(offlineWorkouts) : [];
      workoutsArray.push(workout);
      await AsyncStorage.setItem("offlineWorkouts", JSON.stringify(workoutsArray));
      console.log("Saved workout offline");
      setWorkouts((prev) => [...prev, { ...workout, id: Date.now() }]); // Use temporary ID
      Alert.alert("Workout saved offline");
    } catch (error) {
      console.error("Failed to save workout offline:", error);
    }
  };

  // Update workout via server
  const updateWorkout = async (workout: Workout) => {
    try {
      await axios.put(`${BASE_URL}/${workout.id}`, workout);
      setWorkouts((prevWorkouts) =>
        prevWorkouts.map((w) => (w.id === workout.id ? workout : w))
      );
    } catch (error) {
      console.error("Failed to update workout:", error);
    }
  };

  // Remove workout from server
  const removeWorkout = async (id: number) => {
    try {
      await axios.delete(`${BASE_URL}/${id}`);
      setWorkouts((prevWorkouts) => prevWorkouts.filter((w) => w.id !== id));
    } catch (error) {
      console.error("Failed to remove workout:", error);
    }
  };

  // WebSocket handling
  const checkWebSocketConnection = () => {
    if (!stompClient || !stompClient.connected) {
      console.warn("WebSocket is disconnected. Attempting to reconnect...");
      connectWebSocket(handleWorkoutMessage);
    } else {
      console.log("WebSocket is connected.");
    }
  };

  type WorkoutMessage = {
    operation: "CREATE" | "UPDATE" | "DELETE";
    workout?: Workout;
    id?: number;
  };

  // Connect to WebSocket
  const connectWebSocket = async (onMessage: (message: WorkoutMessage) => void) => {
    if (stompClient && stompClient.connected) {
      console.log("WebSocket is already connected.");
      return;
    }

    const socket = new SockJS(WS_URL);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, async () => {
      console.log("Connected to WebSocket");
      await syncOfflineWorkouts(); // Sync offline workouts if any
      stompClient.subscribe("/topic/workouts", (message: any) => {
        const workoutMessage: WorkoutMessage = JSON.parse(message.body);
        onMessage(workoutMessage);
      });
    });
  };

  // Handle incoming WebSocket messages
  const handleWorkoutMessage = (message: WorkoutMessage) => {
    const { operation, workout, id } = message;
    if (operation === "CREATE" && workout) {
      setWorkouts((prev) => [...prev, workout]);
    } else if (operation === "UPDATE" && workout) {
      setWorkouts((prev) =>
        prev.map((w) => (w.id === workout.id ? workout : w))
      );
    } else if (operation === "DELETE" && id) {
      setWorkouts((prev) => prev.filter((w) => w.id !== id));
    }
  };

  // Disconnect WebSocket
  const disconnectWebSocket = () => {
    if (stompClient) {
      stompClient.disconnect(() => console.log("Disconnected from WebSocket"));
    }
  };

  return (
    <WorkoutsContext.Provider value={{ workouts, addWorkout, updateWorkout, removeWorkout }}>
      {children}
    </WorkoutsContext.Provider>
  );
};

export default WorkoutsProvider;
