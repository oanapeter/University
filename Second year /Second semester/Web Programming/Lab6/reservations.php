

<?php
session_start();

if (!isset($_SESSION['username'])) {
    header("Location: index.php");
    exit();
}

$server = "localhost";
$username = "root";
$password = "";
$db = "hotel";

$conn = new mysqli($server, $username, $password, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "
    SELECT 
        reservations.reservation_id, 

        rooms.room_id, 
        hotels.name AS hotel_name, 
        rooms.category, 
        rooms.type, 
        reservations.check_in, 
        reservations.check_out,
        users.username AS client_username
    FROM 
        reservations
    JOIN 
        rooms ON reservations.room_id = rooms.room_id
    JOIN 
        users ON reservations.client_id = users.id
        JOIN
        hotels ON rooms.hotel_id = hotels.hotel_id
";

$result = $conn->query($sql);

if (!$result) {
    die("Error fetching reservations: " . $conn->error);
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reservations</title>
    
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

        table {
            width: 80%;
            border-collapse: collapse;
            background-color: white;
        }

        table, th, td {
            border: 1px solid black;
        }

        th, td {
            padding: 10px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }

        .back-button {
            background-color: #FF5722;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            
        }

        h1 {
            text-align: center;
            margin-bottom: 20px;
            width: 100%;
        }
    </style>

    <script>
    function confirmDeletion(reservationId) {
        return confirm("Are you sure you want to delete reservation ID " + reservationId + "?");
    }
    </script>
</head>
<body>
    <div>
        <h1>Reservations</h1>

        <table>
            <tr>
                <th>Reservation ID</th>
                <th>Hotel Name</th>
                <th>Client</th>
                <th>Room Category</th>
                <th>Room Type</th>
                <th>Check-In</th>
                <th>Check-Out</th>
                <th>Actions</th>
            </tr>

            <?php
            while ($row = $result->fetch_assoc()) {
                echo "<tr>";
                echo "<td>" . htmlspecialchars($row['reservation_id']) . "</td>";
                echo "<td>" . htmlspecialchars($row['hotel_name']) . "</td>";
                echo "<td>" . htmlspecialchars($row['client_username']) . "</td>";
                echo "<td>" . htmlspecialchars($row['category']) . "</td>";
                echo "<td>" . htmlspecialchars($row['type']) . "</td>";
                echo "<td>" . htmlspecialchars($row['check_in']) . "</td>";
                echo "<td>" . htmlspecialchars($row['check_out']) . "</td>";
                echo "<td>";
                echo "<a href='delete_reservation.php?id=" . htmlspecialchars($row['reservation_id']) . "' onclick='return confirmDeletion(\"" . htmlspecialchars($row['reservation_id']) . "\")'>";
                echo "<button type='button' class='back-button'>Delete</button>";
                echo "</a>";
                echo "</td>";
                echo "</tr>";
            }
            ?>
        </table>
        <br>

        <!-- Button to navigate back to the main page -->
        <div>
            <a href="home.php">
                <button type="button" class="back-button">Back to Home Page</button>
            </a>
        </div>
    </div>
</body>
</html>
