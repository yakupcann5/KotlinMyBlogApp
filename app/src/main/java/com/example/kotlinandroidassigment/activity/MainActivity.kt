package com.example.kotlinandroidassigment.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinandroidassigment.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
//    val Button = button_sayfa_bir
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_sayfa_bir.setOnClickListener(){
            intent= Intent(this,SayfaIkiActivity::class.java)
            startActivity(intent)
        }
    }
}