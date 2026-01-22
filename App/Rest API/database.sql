-- Database Schema untuk Mobile Database Programming
-- PHP 8+ REST API

CREATE DATABASE IF NOT EXISTS mobile_db;
USE mobile_db;

-- Table untuk menyimpan data pengguna/produk
CREATE TABLE IF NOT EXISTS items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Insert sample data
INSERT INTO items (name, description, price) VALUES
('Laptop', 'Laptop Gaming High Performance', 15000000.00),
('Smartphone', 'Smartphone Android Terbaru', 5000000.00),
('Tablet', 'Tablet untuk Produktivitas', 8000000.00);
