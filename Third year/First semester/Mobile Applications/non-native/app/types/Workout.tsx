export type Workout = {
    id: number;
    name: string;
    description: string;
    duration: number;
    intensity: 'Low' | 'Medium' | 'High';
    burnedCalories: number;
  };