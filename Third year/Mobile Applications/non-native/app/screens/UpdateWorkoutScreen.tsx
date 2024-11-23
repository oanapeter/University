import React, { useState } from 'react';
import { View, Text, TextInput, StyleSheet, TouchableOpacity, Alert } from 'react-native';
import { Workout } from '../types/Workout';
import { Picker } from '@react-native-picker/picker'; 

type UpdateWorkoutProps = {
  route: any;
  navigation: any;
};

const UpdateWorkoutScreen: React.FC<UpdateWorkoutProps> = ({ route, navigation }) => {
  const { workout, updateWorkout } = route.params;

  const [name, setName] = useState<string>(workout.name);
  const [description, setDescription] = useState<string>(workout.description);
  const [duration, setDuration] = useState<string>(workout.duration.toString());
  const [intensity, setIntensity] = useState<'Low' | 'Medium' | 'High'>(workout.intensity);
  const [caloriesBurned, setCaloriesBurned] = useState<string>(workout.burnedCalories.toString());

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

  const handleUpdate = () => {
    if (validateInputs()) {
      const updatedWorkout: Workout = {
        ...workout,
        name,
        description,
        duration: Number(duration),
        intensity,
        burnedCalories: Number(caloriesBurned),
      };

      updateWorkout(updatedWorkout); 
      navigation.goBack(); 
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.label}>Workout Name:</Text>
      <TextInput
        style={styles.input}
        value={name}
        onChangeText={setName}
      />
      <Text style={styles.label}>Description:</Text>
      <TextInput
        style={[styles.input, styles.multiLineInput]}
        value={description}
        onChangeText={setDescription}
      />
      <Text style={styles.label}>Duration (minutes):</Text>
      <TextInput
        style={styles.input}
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
        value={caloriesBurned}
        onChangeText={setCaloriesBurned}
        keyboardType="numeric"
      />

      <TouchableOpacity style={styles.updateButton} onPress={handleUpdate}>
        <Text style={styles.buttonText}>Update Workout</Text>
      </TouchableOpacity>
    </View>
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
  updateButton: {
    backgroundColor: '#800080',
    padding: 12,
    borderRadius: 5,
    alignItems: 'center',
    marginTop: 20,
  },
  buttonText: {
    color: '#fff',
    fontWeight: 'bold',
  },
  multiLineInput: {
    height: 100, 
  },
});

export default UpdateWorkoutScreen;
