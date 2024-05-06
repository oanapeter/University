<?php
session_start();

if (isset($_SESSION['username']) && $_SESSION['username'] === 'admin') {
    $username = htmlspecialchars($_SESSION['username']);
} else {
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

$hotel_ids = [];
$sql_hotels = "SELECT hotel_id, name FROM hotels";
$hotel_result = $conn->query($sql_hotels);

if ($hotel_result->num_rows > 0) {
    while ($row = $hotel_result->fetch_assoc()) {
        $hotel_ids[] = $row; // Populate the array
    }
} else {
    die("No hotels available. Please add a hotel first.");
}

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $hotel_id = intval($_POST['hotel_id']);
    $category = htmlspecialchars(trim($_POST['category']));
    $type = htmlspecialchars(trim($_POST['type']));
    $price = floatval($_POST['price']);

    $check_hotel = "SELECT * FROM hotels WHERE hotel_id = $hotel_id";
    $hotel_check_result = $conn->query($check_hotel);

    if ($hotel_check_result->num_rows === 0) {
        echo "Invalid hotel_id. The hotel must exist in the hotels table.";
    } else {
        $sql = "INSERT INTO rooms (hotel_id, category, type, price, status) VALUES ($hotel_id, '$category', '$type', $price, 'available')";

        if ($conn->query($sql) === TRUE) {
            echo "New room added successfully.";
        } else {
            echo "Error: " . $conn->error;
        }
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Room</title>
    <link rel="stylesheet" href="style.css"> 
    <style>
        
        body {
            background: plum;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column; 
        }

        form {
            width: 500px;
            background: white;
            padding: 30px;
            border: 2px solid #ccc;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 15px;
            display: flex;
            flex-direction: column; 
            gap: 10px; 
        }

        input {
            display: block;
            border: 2px solid #ccc;
            width: 95%;
            padding: 10px;
            margin: 10px auto;
            border-radius: 5px;
        }

        label {
            color: #888;
            font-size: 18px;
            padding: 10px;
        }

        button {
            background: plum;
            color: white;
            padding: 10px 15px;
            border: 2px solid #000;
            border-radius: 5px;
            margin: 10px;
            cursor: pointer;
            float: none; 
        }

        button:hover {
            opacity: 0.7;
        }

        h1 {
            text-align: center; 
            margin-bottom: 20px; 
            width: 100%; 
        }
        .back-button {
            background-color: #4CAF50; /* Green for back button */
            color: white;
            border: none;
            padding: 10px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <h1>Add a New Room</h1> 
    <form method="POST" action="add_room.php"> 
        <label for="hotel_id">Hotel ID:</label>
        <select name="hotel_id" id="hotel_id">
            <?php foreach ($hotel_ids as $hotel): ?>
                <option value="<?php echo $hotel['hotel_id']; ?>"><?php echo $hotel['name']; ?></option>
            <?php endforeach; ?>
        </select>

        <label for="category">Category:</label>
        <input type="text" name="category" id="category" required>
        
        <label for="type">Type:</label>
        <input type="text" name="type" id="type" required>
        
        <label for="price">Price:</label>
        <input type="number" step="0.01" name="price" id="price" required>
        
        <button type="submit">Add Room</button> 
    </form>
    <div>
            <a href="home.php">
                <button type="button" class="back-button">Back to Home Page</button>
            </a>
        </div>
</body>
</html>