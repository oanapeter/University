<?php
session_start(); // Start session at the top of the file

// Check if the session variable 'username' is set
if (isset($_SESSION['username'])) {
    $username = htmlspecialchars($_SESSION['username']); // Sanitize the session data
} else {
    // If the session variable is not set, redirect to the login page
    header("Location: index.php");
    exit(); // Exit to prevent further code execution
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <title>Hotel Booking Site</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <main>
        <div class="container">
            <h1>Welcome, <?php echo $username; ?>!</h1> 
            <?php if ($username === 'admin'): ?>
                <a href="add_hotel.php">
                    <button type="button" style="background-color: #4CAF50; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer;">Add Hotel</button>
                </a>
                <a href="add_room.php">
                    <button type="button" style="background-color: #2196F3; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer;">Add Room</button>
                </a>
                <a href="rooms_admin.php">
                    <button type="button" style="background-color: #FF5722; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer;">View Rooms</button>
                </a>
            <?php else: ?>
                <a href="list_rooms.php">
                    <button type="button" style="background-color: #4CAF50; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer;">View Rooms</button>
                </a>
                <a href="reservations.php">
                    <button type="button" style="background-color: #2196F3; color: white; border: none; padding: 10px 20px; border-radius: 5px; cursor: pointer;">View Reservations</button>
                </a>
            <?php endif; ?>
        </div>
    </main>
</body>
</html>
