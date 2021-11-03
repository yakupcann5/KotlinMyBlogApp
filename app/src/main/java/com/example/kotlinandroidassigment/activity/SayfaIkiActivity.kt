package com.example.kotlinandroidassigment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.kotlinandroidassigment.R
import com.example.kotlinandroidassigment.fragment.HomeFragment
import com.example.kotlinandroidassigment.fragment.ProfilFragment
import com.example.kotlinandroidassigment.fragment.ToDoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SayfaIkiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sayfa_iki)
        replaceFragment(HomeFragment())
        initBottomNavigation()
    }
    private fun initBottomNavigation() {
        val bottom = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }
                R.id.profil -> {
                    replaceFragment(ProfilFragment())
                    true
                }
                R.id.todo -> {
                    replaceFragment(ToDoFragment())
                    true
                }
                else -> false
            }
        }
    }

    override fun onBackPressed() {}

    private fun replaceFragment(fragment: Fragment) {
        val changer: FragmentTransaction = supportFragmentManager.beginTransaction()
        changer.replace(R.id.flFragment, fragment)
        changer.commit()
    }
}