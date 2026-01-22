<?php
require_once 'config.php';

$method = $_SERVER['REQUEST_METHOD'];
$conn = getConnection();

switch ($method) {
    case 'GET':
        handleGet($conn);
        break;
    
    case 'POST':
        handlePost($conn);
        break;
    
    default:
        http_response_code(405);
        echo json_encode([
            'status' => 'error',
            'message' => 'Method not allowed'
        ]);
        break;
}

function handleGet($conn) {
    // GET all items
    if (isset($_GET['id'])) {
        // GET single item by ID
        $id = intval($_GET['id']);
        $stmt = $conn->prepare("SELECT * FROM items WHERE id = ?");
        $stmt->bind_param("i", $id);
        $stmt->execute();
        $result = $stmt->get_result();
        
        if ($result->num_rows > 0) {
            $item = $result->fetch_assoc();
            echo json_encode([
                'status' => 'success',
                'data' => $item
            ]);
        } else {
            http_response_code(404);
            echo json_encode([
                'status' => 'error',
                'message' => 'Item not found'
            ]);
        }
        $stmt->close();
    } else {
        // GET all items
        $result = $conn->query("SELECT * FROM items ORDER BY created_at DESC");
        $items = [];
        
        while ($row = $result->fetch_assoc()) {
            $items[] = $row;
        }
        
        echo json_encode([
            'status' => 'success',
            'data' => $items,
            'count' => count($items)
        ]);
    }
}

function handlePost($conn) {
    // Get JSON input
    $input = json_decode(file_get_contents('php://input'), true);
    
    // Validate input
    if (!isset($input['name']) || empty($input['name'])) {
        http_response_code(400);
        echo json_encode([
            'status' => 'error',
            'message' => 'Name is required'
        ]);
        return;
    }
    
    $name = $conn->real_escape_string($input['name']);
    $description = isset($input['description']) ? $conn->real_escape_string($input['description']) : '';
    $price = isset($input['price']) ? floatval($input['price']) : 0.00;
    
    // Insert new item
    $stmt = $conn->prepare("INSERT INTO items (name, description, price) VALUES (?, ?, ?)");
    $stmt->bind_param("ssd", $name, $description, $price);
    
    if ($stmt->execute()) {
        $newId = $conn->insert_id;
        http_response_code(201);
        echo json_encode([
            'status' => 'success',
            'message' => 'Item created successfully',
            'data' => [
                'id' => $newId,
                'name' => $name,
                'description' => $description,
                'price' => $price
            ]
        ]);
    } else {
        http_response_code(500);
        echo json_encode([
            'status' => 'error',
            'message' => 'Failed to create item: ' . $stmt->error
        ]);
    }
    
    $stmt->close();
}

$conn->close();
