import React, { useState } from 'react';
import { View, FlatList, SafeAreaView, StyleSheet, TouchableOpacity, Text } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import WorkoutCard from '../components/WorkoutCard';
import { Workout } from '../types/Workout';

const workoutData: Workout[] = [
  {
    id: 1,
    name: 'Traditional Strength Training',
    description: 'A weighted workout targeting the lower part of the body.',
    duration: 60,
    intensity: 'High',
    burnedCalories: 500,
  },
  {
    id: 2,
    name: 'Stair-Stepper',
    description: 'A cardio workout that mimics climbing stairs, focusing on improving lower body strength and endurance.',
    duration: 30,
    intensity: 'Medium',
    burnedCalories: 300,
  },
];

const MainScreen: React.FC = () => {
  const [workouts, setWorkouts] = useState<Workout[]>(workoutData);
  const navigation = useNavigation<any>();
  const addWorkout = (workout: Workout) => {
    setWorkouts((prevWorkouts) => [...prevWorkouts, workout]);
  };

  const handleUpdateWorkout = (workout: Workout) => {
    navigation.navigate('UpdateWorkout', {
      workout, 
      updateWorkout: handleUpdate, 
    });
  };

  const handleUpdate = (updatedWorkout: Workout) => {
    const updatedWorkouts = workouts.map((w) =>
      w.id === updatedWorkout.id ? updatedWorkout : w
    );
    setWorkouts(updatedWorkouts); 
  };


  const handleDelete = (id: number) => {
    setWorkouts(workouts.filter((workout) => workout.id !== id));
  };

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.header}>
        <Text style={styles.headerText}>MuscleMind</Text>
      </View>

      <FlatList
        data={workouts}
        keyExtractor={(item) => item.id.toString()}
        renderItem={({ item }) => (
          <WorkoutCard
            workout={item}
            onUpdate={() => handleUpdateWorkout(item)} 
            onDelete={() => handleDelete(item.id)}
          />
        )}
      />
      <TouchableOpacity
        style={styles.addButton}
        onPress={() => {
          navigation.navigate('AddWorkout', { addWorkout });
        }}
      >
        <Text style={styles.addButtonText}>+</Text>
      </TouchableOpacity>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    backgroundColor: '#f0f0f0',
  },
  header: {
    backgroundColor: '#800080', 
    alignSelf: 'center', 
    paddingVertical: 10, 
    paddingHorizontal: 20, 
    marginBottom: 10, 
  },
  headerText: {
    fontSize: 24,
    color: '#fff', 
    fontWeight: 'bold',
  },
  addButton: {
    position: 'absolute',
    bottom: 30,
    alignSelf: 'center',
    backgroundColor: '#800080', 
    width: 60,
    height: 60,
    borderRadius: 30,
    justifyContent: 'center',
    alignItems: 'center',
    elevation: 5,
  },
  addButtonText: {
    fontSize: 30,
    color: '#fff',
  },
  button: {
    backgroundColor: '#800080', 
    padding: 10,
    borderRadius: 5,
    marginBottom: 15,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
});

export default MainScreen;
