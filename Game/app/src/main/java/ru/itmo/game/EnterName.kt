package ru.itmo.game

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class EnterName : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.enter_name)
    }

    fun startGame(v: View?) { // information about name
        val editText = findViewById<View>(R.id.enterAName) as EditText
        val name = editText.text.toString()
        val intent = Intent(this, Game::class.java)
        intent.putExtra("name", name);
        startActivity(intent)
    }
}