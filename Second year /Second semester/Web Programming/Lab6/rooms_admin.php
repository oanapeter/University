<?php
session_start();

$server = "localhost";
$username = "root";
$password = "";
$db = "hotel";

$conn = new mysqli($server, $username, $password, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}



if (isset($_POST['delete_room'])) {
    $room_id = $_POST['room_id'];
    $delete_sql = "DELETE FROM rooms WHERE room_id = ?";
    $stmt = $conn->prepare($delete_sql);
    $stmt->bind_param('i', $room_id);
    if (!$stmt->execute()) {
        echo "Error deleting room: " . $stmt->error;
    } 
}

$sql = "SELECT * FROM rooms";
$result = $conn->query($sql);

if (!$result) {
    die("Error fetching rooms: " . $conn->error);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Rooms Management</title>
    
    <style>
        body {
            font-family: sans-serif;
            background-color: plum; /* Plum background */
            display: flex;
            justify-content: center; /* Center horizontally */
            align-items: center; /* Center vertically */
            height: 100vh; /* Full viewport height */
            margin: 0; /* Reset default margins */
        }
        
        .container {
            text-align: center; /* Center all content */
            background-color: white; /* White background for container */
            padding: 20px; /* Padding around content */
            border-radius: 10px; /* Rounded corners for the container */
        }
        
        table {
            border-collapse: collapse;
            width: 100%; /* Full width */
            margin: 20px auto; /* Center table with margin */
        }
        
        table, th, td {
            border: 1px solid black; /* Border for table and cells */
        }
        
        th, td {
            padding: 10px; /* Padding for cells */
            text-align: center; /* Center content in cells */
        }

        .update-button {
            background-color: #2196F3; /* Blue background for update button */
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            text-decoration: none; /* Remove underline from links */
        }
        
        .delete-button {
            background-color: #FF5722; /* Orange background for delete button */
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .back-button {
            background-color: #4CAF50; /* Green for back button */
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
        }
        .back-button:hover {
            opacity: 0.7; /* Slightly transparent on hover */
            cursor: pointer;
        }

    </style>

    <script>
    function confirmDeletion() {
        return confirm("Are you sure you want to delete this room?");
    }
    </script>
</head>
<body>
    <div class="container">
        <h1>Rooms Management</h1>

        <table>
            <tr>
                <th>Room ID</th>
                <th>Category</th>
                <th>Type</th>
                <th>Price</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>

            <?php
            while ($row = $result->fetch_assoc()) {
                echo "<tr>";
                echo "<td>" . htmlspecialchars($row['room_id']) . "</td>";
                echo "<td>" . htmlspecialchars($row['category']) . "</td>";
                echo "<td>" . htmlspecialchars($row['type']) . "</td>";
                echo "<td>" . htmlspecialchars($row['price']) . "</td>";
                
                // Update button linking to the update page
                echo "<td><a href='update_room.php?room_id=" . htmlspecialchars($row['room_id']) . "' class='update-button'>Update</a></td>";
                
                // Delete button with a form submission that calls confirmDeletion()
                echo "<td>";
                echo "<form method='post' style='display:inline;' onsubmit='return confirmDeletion();'>";
                echo "<input type='hidden' name='room_id' value='" . htmlspecialchars($row['room_id']) . "' />";
                echo "<button type='submit' name='delete_room' class='delete-button'>Delete</button>";
                echo "</form>";
                echo "</td>";
                echo "</tr>";
            }
            ?>
        </table>

        <br>
        <a href="home.php">
            <button type="button" class="back-button">Back to Home Page</button>
        </a>
    </div>
</body>
</html>
