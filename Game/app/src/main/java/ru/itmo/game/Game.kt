package ru.itmo.game
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Game : AppCompatActivity() {
    private lateinit var mole1: ImageButton // lazy initialization button
    private lateinit var mole2: ImageButton
    private lateinit var mole3: ImageButton
    private lateinit var mole4: ImageButton
    private lateinit var mole5: ImageButton
    private lateinit var mole6: ImageButton
    private lateinit var mole7: ImageButton
    private lateinit var mole8: ImageButton
    private lateinit var mole9: ImageButton

    private lateinit var count: TextView  // counter
    private lateinit var moleIdArray: IntArray // Array with ID buttons

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_page) // active page

        val arguments = intent.extras   // take name from previous activity
        val name = arguments!!["name"].toString()
        val mainText = findViewById(R.id.hit_mole) as TextView
        mainText.setText(name + " hit the 10 mole to win"); // generate firts TextView

        count = findViewById(R.id.score) // take by ID counter

        moleIdArray = setArrayIntegerIdImageButton() // generate Array

        initUiElements() // initialization

        disablePeat()

        fillMoleArrayList(0)
    }

    override fun onResume() {
        super.onResume()
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                showMole()
                handler.postDelayed(this, 1000) // 1 sec delay
            }
        }, 0)

    }

    private fun setArrayIntegerIdImageButton(): IntArray{
        return intArrayOf(
            R.id.mole1,
            R.id.mole2,
            R.id.mole3,
            R.id.mole4,
            R.id.mole5,
            R.id.mole6,
            R.id.mole7,
            R.id.mole8,
            R.id.mole9
        )
    }

    private fun initUiElements() {
        mole1 = findViewById(R.id.mole1)
        mole2 = findViewById(R.id.mole2)
        mole3 = findViewById(R.id.mole3)
        mole4 = findViewById(R.id.mole4)
        mole5 = findViewById(R.id.mole5)
        mole6 = findViewById(R.id.mole6)
        mole7 = findViewById(R.id.mole7)
        mole8 = findViewById(R.id.mole8)
        mole9 = findViewById(R.id.mole9)
    }

    private fun disablePeat() {
        mole1.isEnabled = false
        mole2.isEnabled = false
        mole3.isEnabled = false
        mole4.isEnabled = false
        mole5.isEnabled = false
        mole6.isEnabled = false
        mole7.isEnabled = false
        mole8.isEnabled = false
        mole9.isEnabled = false
    }

    private fun fillMoleArrayList(notAvailiableMoleId: Int) { // fill array with indexes mole
        for (i in moleIdArray.indices) {
            getListMole().add(moleIdArray[i])
        }
        if (notAvailiableMoleId != 0) getListMole().remove(notAvailiableMoleId)
    }

    private fun getListMole(): ArrayList<Int> { // mole ID
        return ArrayList(moleIdArray.toList())
    }

    @SuppressLint("SetTextI18n")
    private fun showMole() {
        val activeMole = findViewById<ImageButton>( // choose random hole
            getListMole().get(
                Random().nextInt(moleIdArray.size)
            )
        )
        activeMole.setImageResource(R.drawable.mole) // uprise mole
        activeMole.isEnabled = true

        val handler = Handler() // visibility time
        handler.postDelayed(createRunnable(activeMole), 1000) // one step game
        checkClickedOnMole(activeMole) // check click
    }

    @SuppressLint("SetTextI18n")
    private fun checkClickedOnMole(activeMole: ImageButton){ // check click on mole
        val imageButton = findViewById<ImageButton>(activeMole.id)
        imageButton.setOnClickListener(View.OnClickListener {
            imageButton.setImageResource(R.drawable.hit) // hit pics
            activeMole.isEnabled = false
            count.setText((count.getText().toString().toInt() + 1).toString()) // counter++

            if (count.getText().toString().toInt() == 10){ // if counter == 10 then finish the game
                val intent = Intent(this, EndGame::class.java)
                startActivity(intent)
            }
        })
    }

    private fun createRunnable(activeMole: ImageButton): Runnable {
        val runnable = Runnable {
            getListMole().clear()
            fillMoleArrayList(activeMole.id) // refill array
            activeMole.setImageResource(R.drawable.hole) // all pics - hole
            activeMole.isEnabled = false
        }
        return runnable
    }



}