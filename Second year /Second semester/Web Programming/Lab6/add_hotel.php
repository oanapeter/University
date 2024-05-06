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

$error = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $hotel_name = htmlspecialchars(trim($_POST['hotel_name']));
    $hotel_location = htmlspecialchars(trim($_POST['hotel_location']));
    $hotel_description = htmlspecialchars(trim($_POST['hotel_description']));

    if (empty($hotel_name) || empty($hotel_location)) {
        $error = "Hotel name and location are required.";
    } else {
        $stmt = $conn->prepare("INSERT INTO hotels (name, location, description) VALUES (?, ?, ?)");
        $stmt->bind_param("sss", $hotel_name, $hotel_location, $hotel_description);

        if ($stmt->execute()) {
            $success = "Hotel successfully added!";
        } else {
            $error = "Error adding hotel: " . $stmt->error;
        }

        $stmt->close();
    }
}

$conn->close();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Hotel</title>
    <link rel="stylesheet" href="style.css">
    <style>

        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            flex-direction: column; 
            background: plum; 
        }

        form {
            background: white; 
            padding: 30px; 
            border: 2px solid #ccc; 
            border-radius: 15px; 
            box-shadow: 0 0 10px rgba(0,0,0,0.1); 
            display: flex;
            flex-direction: column; 
            gap: 10px; 
            width: 500px; 
        }

        label {
            font-size: 18px; 
            color: #888; 
        }

        input, textarea {
            display: block;
            border: 2px solid #ccc; 
            padding: 10px; 
            border-radius: 5px; 
            width: 100%; 
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

        .error {
            color: red; 
            font-weight: bold; 
        }

        .success {
            color: green; 
            font-weight: bold; 
        }

        h1 {
            text-align: center; 
            width: 100%; 
            margin-bottom: 20px; 
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
    <h1>Add a New Hotel</h1>


    <?php if ($error): ?>
        <p class="error"><?php echo $error; ?></p>
    <?php endif; ?>
    <?php if (isset($success)): ?>
        <p class="success"><?php echo $success; ?></p>
    <?php endif; ?>

    <form action="add_hotel.php" method="POST">
        <label for="hotel_name">Hotel Name:</label>
        <input type="text" id="hotel_name" name="hotel_name" required>
        
        <label for="hotel_location">Hotel Location:</label>
        <input type="text" id="hotel_location" name="hotel_location" required>
        
        <label for="hotel_description">Hotel Description:</label>
        <textarea id="hotel_description" name="hotel_description"></textarea>
        
        <button type="submit">Add Hotel</button>
    </form>
    <div>
            <a href="home.php">
                <button type="button" class="back-button">Back to Home Page</button>
            </a>
        </div>
</body>
</html>
