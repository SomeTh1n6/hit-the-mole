package ru.itmo.game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class EndGame : AppCompatActivity() {

    private lateinit var buttonPlayAgain: Button
    private lateinit var buttonMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)

        buttonPlayAgain = findViewById(R.id.playAgain)
        buttonMenu = findViewById(R.id.menu)

        initButtonEvent()
    }

    private fun startGameActivity() { // go to enter name page
        val intent = Intent(this, EnterName::class.java)
        startActivity(intent)
    }

    private fun mainActivity() { // go to start page
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun initButtonEvent(){ // initialization button
        buttonPlayAgain.setOnClickListener {
            startGameActivity()
        }
        buttonMenu.setOnClickListener {
            mainActivity()
        }
    }

}