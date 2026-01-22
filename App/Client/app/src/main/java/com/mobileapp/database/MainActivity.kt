package com.mobileapp.database

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobileapp.database.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ItemAdapter
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize API Service
        apiService = RetrofitClient.apiService

        // Setup RecyclerView
        adapter = ItemAdapter()
        binding.recyclerViewItems.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewItems.adapter = adapter

        // Load items on start
        loadItems()

        // Setup button click listeners
        binding.buttonGetAll.setOnClickListener {
            loadItems()
        }

        binding.buttonAddItem.setOnClickListener {
            addNewItem()
        }
    }

    private fun loadItems() {
        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                val response = apiService.getAllItems()
                
                if (response.status == "success") {
                    adapter.submitList(response.data)
                    Toast.makeText(
                        this@MainActivity,
                        "Loaded ${response.count} items",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: ${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                    ).show()
            } finally {
                binding.progressBar.visibility = android.view.View.GONE
            }
        }
    }

    private fun addNewItem() {
        val name = binding.editTextName.text.toString().trim()
        val description = binding.editTextDescription.text.toString().trim()
        val priceText = binding.editTextPrice.text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Name is required", Toast.LENGTH_SHORT).show()
            return
        }

        val price = if (priceText.isEmpty()) 0.0 else priceText.toDoubleOrNull() ?: 0.0

        val newItem = ItemRequest(name, description, price)

        lifecycleScope.launch {
            try {
                binding.progressBar.visibility = android.view.View.VISIBLE
                val response = apiService.createItem(newItem)
                
                if (response.status == "success") {
                    Toast.makeText(
                        this@MainActivity,
                        "Item added successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                    
                    // Clear input fields
                    binding.editTextName.text.clear()
                    binding.editTextDescription.text.clear()
                    binding.editTextPrice.text.clear()
                    
                    // Reload items
                    loadItems()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Error: ${response.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            } finally {
                binding.progressBar.visibility = android.view.View.GONE
            }
        }
    }
}
