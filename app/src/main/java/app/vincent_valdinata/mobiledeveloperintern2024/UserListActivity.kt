package app.vincent_valdinata.mobiledeveloperintern2024

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.vincent_valdinata.mobiledeveloperintern2024.adapters.UserAdapter
import app.vincent_valdinata.mobiledeveloperintern2024.databinding.ActivityUserListBinding
import app.vincent_valdinata.mobiledeveloperintern2024.models.User
import app.vincent_valdinata.mobiledeveloperintern2024.network.ApiClient
import app.vincent_valdinata.mobiledeveloperintern2024.network.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserListBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadUsers()
    }

    private fun setupRecyclerView() {
        userAdapter = UserAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)
            adapter = userAdapter
        }
    }

    private fun loadUsers(page: Int = 1) {
        ApiClient.apiService.getUsers(page).enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    response.body()?.let { userResponse ->
                        userAdapter.updateUsers(userResponse.data)
                    }
                } else {
                    Toast.makeText(this@UserListActivity, "Failed to load users", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Toast.makeText(this@UserListActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}