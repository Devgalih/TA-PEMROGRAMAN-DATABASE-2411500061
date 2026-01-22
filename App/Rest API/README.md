# REST API - PHP

## Requirements
- PHP 8.0 atau lebih tinggi
- MySQL/MariaDB
- Apache/Nginx dengan mod_rewrite (opsional)

## Installation

1. Import database:
```bash
mysql -u root -p < database.sql
```

2. Konfigurasi database di `config.php`:
```php
define('DB_HOST', 'localhost');
define('DB_USER', 'root');
define('DB_PASS', '');
define('DB_NAME', 'mobile_db');
```

3. Pastikan web server dapat mengakses folder ini

## API Endpoints

### GET - Get All Items
```
GET /api.php
```

Response:
```json
{
    "status": "success",
    "data": [
        {
            "id": 1,
            "name": "Laptop",
            "description": "Laptop Gaming High Performance",
            "price": "15000000.00",
            "created_at": "2024-01-01 10:00:00",
            "updated_at": "2024-01-01 10:00:00"
        }
    ],
    "count": 1
}
```

### GET - Get Single Item
```
GET /api.php?id=1
```

Response:
```json
{
    "status": "success",
    "data": {
        "id": 1,
        "name": "Laptop",
        "description": "Laptop Gaming High Performance",
        "price": "15000000.00",
        "created_at": "2024-01-01 10:00:00",
        "updated_at": "2024-01-01 10:00:00"
    }
}
```

### POST - Create New Item
```
POST /api.php
Content-Type: application/json

{
    "name": "New Item",
    "description": "Item description",
    "price": 1000000.00
}
```

Response:
```json
{
    "status": "success",
    "message": "Item created successfully",
    "data": {
        "id": 4,
        "name": "New Item",
        "description": "Item description",
        "price": 1000000.00
    }
}
```

## Testing dengan cURL

### GET All Items
```bash
curl http://localhost/api.php
```

### GET Single Item
```bash
curl http://localhost/api.php?id=1
```

### POST New Item
```bash
curl -X POST http://localhost/api.php \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Item","description":"Test Description","price":500000}'
```
