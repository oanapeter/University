import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import MainScreen from './screens/MainScreen';
import AddWorkoutScreen from './screens/AddWorkoutScreen';
import UpdateWorkoutScreen from './screens/UpdateWorkoutScreen';

const Stack = createStackNavigator();

export default function Index() {
  return (

      <Stack.Navigator
        screenOptions={{
          headerShown: false, 
        }}
        initialRouteName="MainScreen"
      >
        <Stack.Screen name="MainScreen" component={MainScreen} />
        <Stack.Screen name="AddWorkout" component={AddWorkoutScreen} />
        <Stack.Screen name="UpdateWorkout" component={UpdateWorkoutScreen} />
      </Stack.Navigator>

  );
}