package com.aplikasi1.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private lateinit var rvHome: RecyclerView

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initRecyclerView()
        initData()

    }
    private fun initData() {
        CoroutineScope(Dispatchers.IO).launch {
            val listUser = ApiConfig.apiService.users()
            CoroutineScope(Dispatchers.Main).launch {
                adapter.set(listUser)
            }
        }
    }

    private suspend fun fetchUsersWithRateLimit(): List<UserModel> {
        val rateLimiter = RateLimiter(10, TimeUnit.MINUTES) // Adjust limits as needed
        val listUser = mutableListOf<UserModel>()

        for (i in 1..100) { // Example: Fetch 100 users
            if (rateLimiter.tryAcquire()) {
                val user = ApiConfig.apiService.users()[i - 1] // Get different users in the loop
                listUser.add(user)
            } else {
                delay(rateLimiter.getTimeUntilReset())
            }
        }

        return listUser
    }

    private fun initView() {
        rvHome = findViewById(R.id.rv_home)
    }

    private fun initRecyclerView() {
        rvHome.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter {
            actionToDetail(it)
        }
        rvHome.adapter = adapter
    }

    private fun actionToDetail(user: UserModel) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(intent)
    }

    private class RateLimiter(private val limit: Int, private val timeUnit: TimeUnit) {
        private var lastResetTime = 0L

        @Synchronized
        fun tryAcquire(): Boolean {
            val currentTime = System.currentTimeMillis()
            if (lastResetTime == 0L || currentTime - lastResetTime >= timeUnit.toMillis(1)) {
                lastResetTime = currentTime
                return true
            }
            return false
        }

        fun getTimeUntilReset(): Long {
            val currentTime = System.currentTimeMillis()
            val timePassed = currentTime - lastResetTime
            return timeUnit.toMillis(1) - timePassed
        }
    }
}