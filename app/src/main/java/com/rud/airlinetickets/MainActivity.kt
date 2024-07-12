package com.rud.airlinetickets

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.rud.airlinetickets.databinding.ActivityMainBinding
import com.rud.airlinetickets.fragment.MainFragment
import com.rud.airlinetickets.fragment.PlaceholderFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceFragment(MainFragment())
        setUpBottomNavigation()
    }

    private fun setUpBottomNavigation() = binding.bottomNavigationView.apply {
        setOnItemSelectedListener {
            when(it.itemId){
                R.id.flight_tickets -> replaceFragment(MainFragment())
                else -> replaceFragment(PlaceholderFragment())

            }
            true
        }
    }

    private fun replaceFragment(fragment : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_fragment,fragment)
        fragmentTransaction.commit()
    }
}