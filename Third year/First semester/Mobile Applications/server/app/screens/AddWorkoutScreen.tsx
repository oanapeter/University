import React, { useContext, useState } from 'react';
import { Text, TextInput, StyleSheet, TouchableOpacity, ScrollView, Alert } from 'react-native';
import { Picker } from '@react-native-picker/picker';
import { useNavigation } from '@react-navigation/native';
import { WorkoutsContext } from '../repository/repository'; // Make sure WorkoutsContext is imported

const AddWorkoutScreen: React.FC = () => {
  const { addWorkout } = useContext(WorkoutsContext); // Directly use the context here
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

  const handleAddWorkout = async () => {
    if (validateInputs()) {
      const workout = {
        name,
        description,
        duration: Number(duration), // Ensure duration is a number
        intensity, // intensity is already correctly typed as 'Low' | 'Medium' | 'High'
        burnedCalories: Number(caloriesBurned), // Ensure burnedCalories is a number
      };

      // Debugging: Log the workout data to verify it's correct
      console.log("Adding workout:", workout);

      try {
        // Pass the workout object to addWorkout function
        await addWorkout(workout);
        navigation.goBack(); // Navigate back to the previous screen after adding the workout
      } catch (error) {
        console.error("Failed to add workout:", error);
        Alert.alert('Error', 'Failed to add workout. Please try again.');
      }
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
        onValueChange={(itemValue) => setIntensity(itemValue as 'Low' | 'Medium' | 'High')}
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