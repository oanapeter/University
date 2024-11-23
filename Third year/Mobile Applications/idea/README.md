# Project Idea

## 1. Description
**“MuscleMind” – Your Personal Workout Journal**  
MuscleMind is a simple and intuitive app that helps users log their daily workouts, track their fitness progress, and stay motivated on their fitness journey. Whether you’re lifting weights, running, or practicing yoga, the app keeps a record of each session, making it easy to see your progress over time. Users can easily add, delete, or update workouts, ensuring their fitness log is always accurate and tailored to their needs.

## 2. Domain Details
**Workout** - represents the domain entity:
- **id**: unique identifier for each workout entry
- **name**: the name of the workout (e.g., “Traditional Strength Training”, “Stair-Stepper”)
- **description**: a brief description of the workout, including details about the main muscle group targeted
- **duration**: the total time spent on the workout (in minutes)
- **intensity**: a field indicating the perceived effort of the workout, categorized as Low, Medium, or High. This helps users understand the workout's difficulty and tailor their routines based on their fitness levels and goals. For example, a "High" intensity workout might include heavy lifting or high-intensity interval training (HIIT), while a "Low" intensity workout could involve stretching or light cardio.
- **burnedCalories**: an estimate of the number of calories burned during the workout session. This helps users track their energy expenditure and provides additional insight into the effectiveness of each workout, especially for those focused on weight loss or calorie management.

## 3. CRUD Operations
- **Create**: At the bottom of the page, users will find a “+” button. Clicking this button directs them to a form where they can input the details for a new workout (name, description, duration, and intensity). Once they hit submit, the new workout will be added to their list. New workout entries are initially saved to the local database when users add them offline. This allows users to log workouts even without an internet connection.
- **Read**: All workouts are displayed on the screen, providing users with easy access to workout details. This feature allows for effortless browsing and helps users track their progress. Users can view all previously logged workouts stored in the local database at any time, providing access to workout details regardless of their connectivity status.
- **Update**: Each workout entry includes an "Update" button beside it. When this button is clicked, users are taken to a form similar to the "Add Workout" form, where they can edit any workout fields. After submitting the form, the modifications are saved, and the workout details are updated accordingly. If users attempt to update a workout entry while offline, a message will be displayed stating that the operation is unavailable.
- **Delete**: Each workout entry also features a "Delete" button. When clicked, the selected workout will be permanently removed from the list. Similar to updates, if users try to delete a workout while offline, the app will show a message indicating that the operation is unavailable.

## 4. Online vs. Offline
The MuscleMind app is designed to provide a seamless experience for users in both online and offline modes, ensuring they can track their workouts regardless of their internet connection:

- **Online Mode**: When connected to the internet, all operations (Create, Read, Update, Delete) are fully operational. Any changes made to workouts are instantly saved to the server.
- **Offline Mode**: Even when offline, users can still view their list of workouts and add new entries. However, operations such as updating or deleting workouts will be temporarily disabled until the device reconnects to the internet. Once a connection is restored, any new workouts will be synced with the server.

