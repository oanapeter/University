import React from 'react';
import { View, Text, StyleSheet, TouchableOpacity, Alert } from 'react-native';
import { Workout } from '../types/Workout';

type WorkoutProps = {
  workout: Workout; 
  onUpdate: () => void;
  onDelete: () => void;
};

const WorkoutCard: React.FC<WorkoutProps> = ({ workout, onUpdate, onDelete }) => {

  const handleDelete = () => {
    Alert.alert(
      "Confirm Delete",
      "Are you sure you want to delete this workout?",
      [
        {
          text: "Cancel",
          style: "cancel",
        },
        {
          text: "Delete",
          style: "destructive",
          onPress: onDelete,  
        },
      ]
    );
  };

  return (
    <View style={styles.card}>
      <Text style={styles.title}>{workout.name}</Text>
      <Text>{workout.description}</Text>
      <Text>Duration: {workout.duration} minutes</Text>
      <Text>Intensity: {workout.intensity}</Text>
      <Text>Calories Burned: {workout.burnedCalories} kcal</Text>
      <View style={styles.buttonContainer}>
        <TouchableOpacity style={styles.updateButton} onPress={onUpdate}>
          <Text style={styles.buttonText}>Update</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.deleteButton} onPress={handleDelete}>
          <Text style={styles.buttonText}>Delete</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
    card: {
      backgroundColor: '#fff',
      padding: 15,
      borderRadius: 8,
      marginVertical: 8,
      shadowColor: '#000',
      shadowOffset: { width: 0, height: 2 },
      shadowOpacity: 0.2,
      shadowRadius: 4,
      elevation: 3,
    },
    title: {
      fontSize: 24, 
      fontWeight: 'bold',
    },
    description: {
      fontSize: 20, 
      marginTop: 5,  
    },
    details: {
      fontSize: 18, 
      marginTop: 5,  
    },
    buttonContainer: {
      flexDirection: 'row',
      justifyContent: 'space-between',
      marginTop: 10,
    },
    updateButton: {
      backgroundColor: '#800080',
      padding: 10,
      borderRadius: 5,
    },
    deleteButton: {
      backgroundColor: '#800080',
      padding: 10,
      borderRadius: 5,
    },
    buttonText: {
      color: '#fff',
      fontWeight: 'bold',
    },
  });

export default WorkoutCard;