<?php
// Start the session
session_start();

// Redirect if not authenticated
if (!isset($_SESSION['username'])) {
    header("Location: index.php");
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

// Get the reservation ID from the GET parameter
$reservation_id = $_GET['id'];

// Get the room ID from the reservation
$sql = "SELECT room_id FROM reservations WHERE reservation_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $reservation_id);
$stmt->execute();
$stmt->bind_result($room_id);
$stmt->fetch();
$stmt->close();

// Delete the reservation
$sql = "DELETE FROM reservations WHERE reservation_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $reservation_id);
$stmt->execute();

// Set the room status to available
$sql = "UPDATE rooms SET status = 'available' WHERE room_id = ?";
$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $room_id);
$stmt->execute();

$stmt->close();

// Redirect back to reservations page
header("Location: reservations.php");
exit();
?>
