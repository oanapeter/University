<?php
session_start(); // Start the session

// Database connection setup
$server = "localhost";
$username = "root";
$password = "";
$db = "hotel";

$conn = new mysqli($server, $username, $password, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Get client ID from session
$username = isset($_SESSION["username"]) ? $_SESSION["username"] : null;
$client_id = 0;

if ($username) {
    $client_sql = "SELECT id FROM users WHERE username = ?";
    $client_stmt = $conn->prepare($client_sql);
    $client_stmt->bind_param("s", $username);
    $client_stmt->execute();
    $client_result = $client_stmt->get_result();

    if ($client_result->num_rows > 0) {
        $client_row = $client_result->fetch_assoc();
        $client_id = $client_row["id"];
    } else {
        die("Error: User not found.");
    }

    $client_result->free();
    $client_stmt->close();
} else {
    die("Error: Invalid session.");
}

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    if (isset($_POST["room_id"])) {
        // Fetch room_id from URL
        $room_id = isset($_GET['room_id']) ? intval($_GET['room_id']) : 0;

    } else {
        die("Error: Room ID is missing.");
    }

    // Validate that the room exists
    $room_check_sql = "SELECT room_id FROM rooms WHERE room_id = ?";
    $room_check_stmt = $conn->prepare($room_check_sql);
    $room_check_stmt->bind_param("i", $room_id);
    $room_check_stmt->execute();
    $room_check_result = $room_check_stmt->get_result();

    if ($room_check_result->num_rows === 0) {
        error_log("Room check SQL: " . $room_check_sql);
    die("Error: Room not found. Room ID: $room_id");
    }

    // Reservation data
    $check_in = $_POST["check_in"];
    $check_out = $_POST["check_out"];

    // Insert reservation
    $insert_sql = "INSERT INTO reservations (room_id, client_id, check_in, check_out) VALUES (?, ?, ?, ?)";
    $stmt = $conn->prepare($insert_sql);
    $stmt->bind_param("iiss", $room_id, $client_id, $check_in, $check_out);

    if ($stmt->execute()) {
        // Update room status
        $update_sql = "UPDATE rooms SET status = 'occupied' WHERE room_id = ?";
        $update_stmt = $conn->prepare($update_sql);
        $update_stmt->bind_param("i", $room_id);

        if ($update_stmt->execute()) {
            echo "Reservation successfully created, and room status updated.";
        } else {
            echo "Error updating room status: " . $conn->error;
        }

        $update_stmt->close();
    } else {
        echo "Error adding reservation: " . $stmt->error;
    }

    $stmt->close();
}

$conn->close();
?>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Book a Reservation</title>
    <style>
        body {
            background-color: plum;
            font-family: sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .form-container {
            max-width: 600px;
            background-color: white;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
            text-align: center;
        }
        .form-container h2 {
            margin-top: 0;
        }
        .form-container label {
            display: block;
            margin-top: 10px;
            text-align: left;
        }
        .form-container input {
            width: 90%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ddd;
        }
        .form-container button {
            width: 100%;
            padding: 10px;
            background-color: plum;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .form-container button:hover {
            background-color: darkorchid;
        }
        .back-button {
            padding: 10px;
            background-color: green;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer.
        }
        .back-button:hover {
            background-color: darkgreen.
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Book a Reservation</h2>
        <form method="POST">
    <input type="hidden" name="room_id" value="<?php echo $room_id; ?>">
    <label for="check_in">Check-In Date:</label>
    <input type="date" name="check_in" required>
    <label for="check_out">Check-Out Date:</label>
    <input type="date" name="check_out" required>
    <button type="submit">Book Reservation</button>
</form>
        <br>
        <a class="back-button" href="list_rooms.php">Back to Room List</a>
    </div>
</body>
</html>
