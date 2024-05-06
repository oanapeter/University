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
$page = isset($_POST['page']) ? intval($_POST['page']) : 1;
$offset = ($page - 1) * $items_per_page;

$hotel_name = isset($_POST['hotel_name']) ? $_POST['hotel_name'] : '';
$category = isset($_POST['category']) ? $_POST['category'] : '';
$type = isset($_POST['type']) ? $_POST['type'] : '';
$max_price = isset($_POST['price']) ? intval($_POST['price']) : null;

$query = " 
    SELECT rooms.room_id, 
           hotels.name AS hotel_name, 
           rooms.category, 
           rooms.type, 
           rooms.price 
    FROM rooms 
    INNER JOIN hotels ON rooms.hotel_id = hotels.hotel_id 
    WHERE rooms.status = 'available'
";

$filters = [];
$param_types = '';

if ($hotel_name) {
    $query .= " AND hotels.name LIKE ?";
    $filters[] = '%' . $hotel_name . '%';
    $param_types .= 's'; // Adding 's' for string type
}

if ($category) {
    $query .= " AND rooms.category LIKE ?";
    $filters[] = '%' . $category . '%';
    $param_types .= 's'; // Adding 's' for string type
}

if ($type) {
    $query .= " AND rooms.type LIKE ?";
    $filters[] = '%' . $type . '%';
    $param_types .= 's'; // Adding 's' for string type
}

if ($max_price) {
    $query .= " AND rooms.price <= ?";
    $filters[] = $max_price;
    $param_types .= 'i'; // Adding 'i' for integer type
}

$query .= " LIMIT $items_per_page OFFSET $offset";

$stmt = $conn->prepare($query);

if (!empty($param_types)) {
    $stmt->bind_param($param_types, ...$filters); // Use correct parameter types
}

$stmt->execute();
$result = $stmt->get_result();

$rooms = [];

while ($row = $result->fetch_assoc()) {
    $rooms[] = [
        'hotel_name' => htmlspecialchars($row['hotel_name']),
        'category' => htmlspecialchars($row['category']),
        'type' => htmlspecialchars($row['type']),
        'price' => htmlspecialchars($row['price']),
        'room_id' => htmlspecialchars($row['room_id']),
    ];
}

$sql_count = "SELECT COUNT(*) as total FROM rooms WHERE rooms.status = 'available'";

$count_stmt = $conn->prepare($sql_count);
$count_stmt->execute();
$count_result = $count_stmt->get_result();

$total_rooms = $count_result->fetch_assoc()['total'];
$total_pages = ceil($total_rooms / $items_per_page);

$response = [
    'rooms' => $rooms,
    'total_pages' => $total_pages,
    'previous_page' => $page > 1 ? $page - 1 : null,
    'next_page' => $page < $total_pages ? $page + 1 : null, // Fix by using `$page`
];

header('Content-Type: application/json');
echo json_encode($response);

$conn->close();
?>
