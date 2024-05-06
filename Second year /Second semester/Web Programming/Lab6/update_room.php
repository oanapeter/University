<?php
// Start the session to ensure the user is logged in
session_start();

// Redirect to login if not authenticated
if (!isset($_SESSION['username'])) {
    header("Location: index.php");
    exit();
}

// Check if the user is an admin
if ($_SESSION['username'] !== 'admin') {
    echo "Access Denied: Admins only.";
    exit();
}

// Database connection setup
$server = "localhost";
$username = "root";
$password = "";
$db = "hotel";

$conn = new mysqli($server, $username, $password, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Fetch the room data if a room ID is provided
$room_id = isset($_GET['room_id']) ? intval($_GET['room_id']) : 0;

if ($room_id > 0) {
    $sql = "SELECT * FROM rooms WHERE room_id = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param('i', $room_id);
    $stmt->execute();
    $result = $stmt->get_result();

    if ($result->num_rows == 0) {
        echo "No room found with the specified ID.";
        exit();
    }

    $room = $result->fetch_assoc();
} else {
    echo "Invalid room ID.";
    exit();
}

// Handle form submission for room update
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $category = isset($_POST['category']) ? $_POST['category'] : '';
    $type = isset($_POST['type']) ? $_POST['type'] : '';
    $price = isset($_POST['price']) ? floatval($_POST['price']) : 0;

    if ($category === '' || $type === '' || $price <= 0) {
        echo "Invalid input. Please provide valid category, type, and price.";
    } else {
        $update_sql = "UPDATE rooms SET category = ?, type = ?, price = ? WHERE room_id = ?";
        $stmt = $conn->prepare($update_sql);
        $stmt->bind_param('ssdi', $category, $type, $price, $room_id);

        if (!$stmt->execute()) {
            echo "Error updating room: " . $stmt->error;
        } else {
            echo "Room updated successfully.";
        }
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update Room</title>
    <style>
        body {
            background-color: plum; /* Set the background to plum */
            font-family: sans-serif; /* Use a sans-serif font */
            display: flex;
            justify-content: center; /* Center the content horizontally */
            align-items: center; /* Center the content vertically */
            height: 100vh; /* Make the height fill the viewport */
        }

        .container {
            text-align: center; /* Center all content */
            background-color: white; /* White background */
            padding: 20px; /* Padding around content */
            border-radius: 10px; /* Rounded corners */
        }

        form {
            text-align: left; /* Left-align form content */
        }

        label {
            display: block; /* Display label on a new line */
            margin-bottom: 10px; /* Space below label */
        }

        input, button {
            padding: 10px; /* Padding around form elements */
            border-radius: 5px; /* Rounded corners */
            border: 1px solid black; /* Border around form elements */
        }

        button {
            background-color: #4CAF50; /* Green background */
            color: white; /* White text */
            cursor: pointer; /* Pointer on hover */
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Update Room</h1>

        <form method="post">
            <label>
                Category:
                <input type="text" name="category" placeholder="Enter category" value="<?php echo htmlspecialchars($room['category']); ?>" />
            </label>

            <label>
                Type:
                <input type="text" name="type" placeholder="Enter type" value="<?php echo htmlspecialchars($room['type']); ?>" />
            </label>

            <label>
                Price:
                <input type="number" step="0.01" name="price" placeholder="Enter price" value="<?php echo htmlspecialchars($room['price']); ?>" />
            </label>

            <button type="submit">Update Room</button>
        </form>

        <br>
        <a href="rooms_admin.php">
            <button type="button">Back to Rooms Management</button>
        </a>
    </div>
</body>
</html>
