package com.example.testridenow

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testridenow.API.Motorcycle
import com.example.testridenow.Retrofit.RetrofitClient
import com.example.testridenow.Adapter.MotorcycleAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var motorcycleAdapter: MotorcycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        motorcycleAdapter = MotorcycleAdapter(listOf())
        recyclerView.adapter = motorcycleAdapter

        // Fetch motorcycles and display them
        fetchMotorcycles()

        // Example: Add a motorcycle
        addMotorcycle("Yamaha", "R1", 2022, 200)
    }

    private fun fetchMotorcycles() {
        val call = RetrofitClient.apiService.getMotorcycles()
        call.enqueue(object : Callback<List<Motorcycle>> {
            override fun onResponse(
                call: Call<List<Motorcycle>>,
                response: Response<List<Motorcycle>>
            ) {
                if (response.isSuccessful) {
                    val motorcycles = response.body() ?: emptyList()
                    motorcycleAdapter.updateData(motorcycles)
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to fetch motorcycles: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Motorcycle>>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun addMotorcycle(name: String, model: String, year: Int, power: Int) {
        val call = RetrofitClient.apiService.addMotorcycle(name, model, year, power)

        call.enqueue(object : Callback<Motorcycle> {
            override fun onResponse(call: Call<Motorcycle>, response: Response<Motorcycle>) {
                if (response.isSuccessful) {
                    val motorcycle = response.body()
                    Toast.makeText(
                        this@MainActivity,
                        "Motorcycle added: ${motorcycle?.name}",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Refresh the list after adding
                    fetchMotorcycles()
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "Failed to add motorcycle: ${response.message()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<Motorcycle>, t: Throwable) {
                Toast.makeText(
                    this@MainActivity,
                    "Error: ${t.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}
