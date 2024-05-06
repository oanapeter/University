

<?php
$server = "localhost";
$username = "root";
$password = "";
$db = "hotel";

$conn = new mysqli($server, $username, $password, $db);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$items_per_page = 4;
$page = isset($_GET['page']) ? intval($_GET['page']) : 1;
$offset = ($page - 1) * $items_per_page;

$sql = "
    SELECT rooms.room_id, 
           hotels.name AS hotel_name, 
           rooms.category, 
           rooms.type, 
           rooms.price 
    FROM rooms 
    INNER JOIN hotels ON rooms.hotel_id = hotels.hotel_id 
    WHERE rooms.status = 'available' 
    LIMIT $items_per_page OFFSET $offset
";
$result = $conn->query($sql);

if (!$result) {
    die("Error: " . $conn->error);
}

$sql_count = "SELECT COUNT(*) as total FROM rooms";
$count_result = $conn->query($sql_count);
$total_rooms = $count_result->fetch_assoc()['total'];
$total_pages = ceil($total_rooms / $items_per_page);
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hotel Rooms</title>
    <style>
        body {
            background-color: plum; /* Set page background to plum */
            font-family: sans-serif; /* Use sans-serif font family */
            margin: 0; /* Reset default margins */
            height: 100vh; /* Fill the entire viewport height */
        }
        .center-wrapper {
            display: flex; /* Use flex layout */
            flex-direction: column; /* Arrange items vertically */
            justify-content: center; /* Center vertically */
            align-items: center; /* Center horizontally */
            height: 100%; /* Full height for centering */
        }
        table {
            width: 50%; /* Fixed width for centering */
            border-collapse: collapse;
            background-color: white; /* Background color for the table */
        }
        table, th, td {
            border: 1px solid black; /* Border for table */
        }
        th, td {
            padding: 8px;
            text-align: left; /* Align text to the left within the table */
        }
        .pagination {
            margin: 10px 0; /* Margin between table and pagination */
            display: flex; /* Flex layout for centering pagination */
            justify-content: center; /* Center pagination links */
        }
        .pagination a {
            text-decoration: none; /* Remove text decoration */
            color: blue; /* Set link color */
            padding: 5px; /* Space between links */
        }
        .home-button { /* Styling for the home button */
            background-color: #4CAF50; /* Green background */
            color: white; /* White text */
            padding: 10px; /* Padding for button */
            text-decoration: none; /* Remove text decoration */
            border: none; /* No border */
            border-radius: 5px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor on hover */
            margin-top: 20px; /* Spacing above the button */
        }
        .filter-form {
            display: flex; /* Use flex layout */
            gap: 10px; /* Spacing between form elements */
            justify-content: center; /* Center the form elements */
            align-items: center; /* Align form elements vertically */
            margin: 20px 0; /* Spacing above and below the form */
        }

        .filter-form input {
            padding: 5px; /* Padding for input fields */
            border: 1px solid #ccc; /* Light gray border */
            border-radius: 4px; /* Slightly rounded corners */
        }

        .filter-form label {
            font-weight: bold; /* Bold font for labels */
        }

        .filter-form button {
            padding: 8px; /* Padding for button */
            background-color: #4CAF50; /* Green background */
            color: white; /* White text */
            border: none; /* No border */
            border-radius: 5px; /* Rounded corners */
            cursor: pointer; /* Pointer cursor on hover */
        }
    </style>
    <script src="script.js"></script>
</head>
<body>
<form id="filter-form" class="filter-form">  <!-- Form for filtering -->
    <label for="hotel-name">Hotel Name:</label>
    <input type="text" id="hotel-name" name="hotel_name">

    <label for="category">Category:</label>
    <input type="text" id="category" name="category">

    <label for="type">Type:</label>
    <input type="text" id="type" name="type">

    <label for="price">Max Price:</label>
    <input type="number" id="price" name="price">

    <button type="button" onclick="filterRooms()">Search</button>  <!-- Button to trigger AJAX -->
</form>

    <div class="center-wrapper">  <!-- Centered wrapper -->
        <h1>Hotel Rooms</h1>  <!-- Centered title -->

        <table>  <!-- Centered table -->
            <tr>
                <th>Hotel Name</th>
                <th>Category</th>
                <th>Type</th>
                <th>Price</th>
                <th>Book</th> <!-- "Book" column for reservation -->
            </tr>
            <?php
            while ($row = $result->fetch_assoc()) {
                echo "<tr>";
                echo "<td>" . htmlspecialchars($row['hotel_name']) . "</td>";
                echo "<td>" . htmlspecialchars($row['category']) . "</td>";
                echo "<td>" . htmlspecialchars($row['type']) . "</td>";
                echo "<td>" . htmlspecialchars($row['price']) . "</td>";
                // "Book" button linking to add_reservation.php with the room_id
                echo "<td><a href='add_reservation.php?room_id=" . $row['room_id'] . "'>Book</a></td>";
                echo "</tr>";
            }
            ?>
        </table>
        <div class="pagination">  <!-- Centered pagination -->
            <?php
            if ($page > 1) {
                echo "<a href='?page=" . ($page - 1) . "'>Previous</a>";  
            }

            for ($i = 1; $i <= $total_pages; $i++) {
                echo "<a href='?page=" . $i . "'>$i</a>";  
            }

            if ($page < $total_pages) {
                echo "<a href='?page=" . ($page + 1) . "'>Next</a>";  
            }
            ?>
        </div>

        <!-- Button to go back to the home page -->
        <a href="home.php" class="home-button">Back to Home Page</a>
    </div>
    
</body>
</html>
