import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, TouchableOpacity, ScrollView, Alert } from 'react-native';
import { Picker } from '@react-native-picker/picker';
import { Workout } from '../types/Workout';
import { useNavigation, useRoute } from '@react-navigation/native';

const AddWorkoutScreen: React.FC = () => {
  const route = useRoute();
  const { addWorkout } = route.params as { addWorkout: (workout: Workout) => void };
  const navigation = useNavigation();

  const [name, setName] = useState<string>('');
  const [description, setDescription] = useState<string>('');
  const [duration, setDuration] = useState<string>('');
  const [intensity, setIntensity] = useState<'Low' | 'Medium' | 'High'>('Low');
  const [caloriesBurned, setCaloriesBurned] = useState<string>('');
  const validateInputs = () => {
    if (!name || !description) {
      Alert.alert('Validation Error', 'Please fill out the name and description.');
      return false;
    }

    if (!duration || isNaN(Number(duration)) || Number(duration) <= 0) {
      Alert.alert('Validation Error', 'Please enter a valid positive number for duration.');
      return false;
    }

    if (!caloriesBurned || isNaN(Number(caloriesBurned)) || Number(caloriesBurned) <= 0) {
      Alert.alert('Validation Error', 'Please enter a valid positive number for calories burned.');
      return false;
    }

    return true;
  };


  const handleAddWorkout = () => {
    if (validateInputs()) {
      const newWorkout: Workout = {
        id: Math.floor(Math.random() * 1000000), 
        name,
        description,
        duration: Number(duration),
        intensity,
        burnedCalories: Number(caloriesBurned),
      };

      addWorkout(newWorkout); 
      navigation.goBack(); 
    }
  };

  return (
    <ScrollView contentContainerStyle={styles.container}>
      <Text style={styles.label}>Workout Name:</Text>
      <TextInput
        style={styles.input}
        placeholder="Enter workout name"
        value={name}
        onChangeText={setName}
      />
      
      <Text style={styles.label}>Description:</Text>
      <TextInput
        style={[styles.input, styles.multiLineInput]} 
        placeholder="Enter description"
        value={description}
        onChangeText={setDescription}
        multiline
      />
      
      <Text style={styles.label}>Duration (minutes):</Text>
      <TextInput
        style={styles.input}
        placeholder="Enter duration"
        value={duration}
        onChangeText={setDuration}
        keyboardType="numeric"
      />
      
      <Text style={styles.label}>Intensity:</Text>
      <Picker
        selectedValue={intensity}
        onValueChange={(itemValue) => setIntensity(itemValue)}
        style={styles.input}
      >
        <Picker.Item label="Low" value="Low" />
        <Picker.Item label="Medium" value="Medium" />
        <Picker.Item label="High" value="High" />
      </Picker>
      
      <Text style={styles.label}>Calories Burned:</Text>
      <TextInput
        style={styles.input}
        placeholder="Enter calories burned"
        value={caloriesBurned}
        onChangeText={setCaloriesBurned}
        keyboardType="numeric"
      />

      <TouchableOpacity style={styles.addButton} onPress={handleAddWorkout}>
        <Text style={styles.addButtonText}>Add Workout</Text>
      </TouchableOpacity>

      <TouchableOpacity style={styles.goBackButton} onPress={() => navigation.goBack()}>
        <Text style={styles.goBackButtonText}>Cancel</Text>
      </TouchableOpacity>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flexGrow: 1,
    padding: 20,
    backgroundColor: '#f9f9f9',
    justifyContent: 'space-between', 
    paddingBottom: 20, 
  },
  label: {
    fontSize: 18,
    marginVertical: 8,
  },
  input: {
    borderWidth: 1,
    borderColor: '#ccc',
    padding: 12,
    borderRadius: 5,
    marginBottom: 15,
    fontSize: 16,
  },
  multiLineInput: {
    height: 100, 
  },
  addButton: {
    backgroundColor: '#800080', 
    paddingVertical: 12,
    borderRadius: 5,
    marginTop: 20,
    alignItems: 'center',
  },
  addButtonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
  },
  goBackButton: {
    marginTop: 10,
    paddingVertical: 12,
    backgroundColor: '#800080', 
    borderRadius: 5,
    alignItems: 'center',
  },
  goBackButtonText: {
    color: '#fff',
    fontSize: 18,
    fontWeight: 'bold',
  },
});

export default AddWorkoutScreen;
