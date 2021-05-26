package com.example.tictactoe

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text
import java.text.Normalizer

class MainActivity : AppCompatActivity() {

    var count  = 0
    private var playerOneTurn: Boolean = true
    private var playerOneMoves = mutableListOf<Int>()
    private var playerTwoMoves = mutableListOf<Int>()

    private val possibleWins: Array<List<Int>> = arrayOf(

        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),

        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(3, 6, 9),

        listOf(1, 5, 9),
        listOf(3, 5, 7)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val bu1: Button = findViewById(R.id.b1)
        val bu2: Button = findViewById(R.id.b2)
        val bu3: Button = findViewById(R.id.b3)
        val bu4: Button = findViewById(R.id.b4)
        val bu5: Button = findViewById(R.id.b5)
        val bu6: Button = findViewById(R.id.b6)
        val bu7: Button = findViewById(R.id.b7)
        val bu8: Button = findViewById(R.id.b8)
        val bu9: Button = findViewById(R.id.b9)
        val n1: TextView = findViewById(R.id.p1)
        val n2: TextView = findViewById(R.id.p2)
        val name1: TextView = findViewById(R.id.p1Name)
        val name2: TextView = findViewById(R.id.p2Name)
        val res : Button = findViewById(R.id.reset)

        bu1.setOnClickListener{
            count++
            recordMove(bu1, 1, n1, n2, name1, name2, res)
        }
        bu2.setOnClickListener{
            count++
            recordMove(bu2, 2, n1, n2, name1, name2, res)
        }
        bu3.setOnClickListener{
            count++
            recordMove(bu3, 3, n1, n2, name1, name2, res)
        }
        bu4.setOnClickListener{
            count++
            recordMove(bu4, 4, n1, n2, name1, name2, res)

        }
        bu5.setOnClickListener{
            count++
            recordMove(bu5, 5, n1, n2, name1, name2, res)

        }
        bu6.setOnClickListener{
            count++
            recordMove(bu6, 6, n1, n2, name1, name2, res)

        }
        bu7.setOnClickListener{
            count++
            recordMove(bu7, 7, n1, n2, name1, name2, res)

        }
        bu8.setOnClickListener{
            count++
            recordMove(bu8, 8, n1, n2, name1, name2, res)

        }
        bu9.setOnClickListener{
            count++
            recordMove(bu9, 9, n1, n2, name1, name2, res)

        }
        res.setOnClickListener{
            bu1.text = ""
            bu1.isClickable = true

            bu2.text = ""
            bu2.isClickable = true

            bu3.text = ""
            bu3.isClickable = true

            bu4.text = ""
            bu4.isClickable = true

            bu5.text = ""
            bu5.isClickable = true

            bu6.text = ""
            bu6.isClickable = true

            bu7.text = ""
            bu7.isClickable = true

            bu8.text = ""
            bu8.isClickable = true

            bu9.text = ""
            bu9.isClickable = true

            name1.text = ""
            name2.text = ""
            n1.setTextColor(
                ContextCompat.getColor(this, R.color.black))
            n1.setTypeface(null, Typeface.NORMAL)
            n2.setTextColor(
                ContextCompat.getColor(this, R.color.black))
            n2.setTypeface(null, Typeface.NORMAL)
            count = 0
            playerOneTurn = true
            playerOneMoves.clear()
            playerTwoMoves.clear()
        }

    }

    fun recordMove(button : Button, id1 : Int, n1 : TextView, n2 : TextView, name1 : TextView, name2 : TextView, res : Button){

        if(playerOneTurn){
            playerOneMoves.add(id1)
            button.text = "X"
            button.isClickable = false
            if(checkWin(playerOneMoves)){
                showWinMessage(name1)
                res.performClick()
            }else{
                n1.setTextColor(
                    ContextCompat.getColor(this, R.color.black))
                n1.setTypeface(null, Typeface.NORMAL)
                n2.setTextColor(
                    ContextCompat.getColor(this, R.color.design_default_color_primary_dark))
                n2.setTypeface(null, Typeface.BOLD)
                playerOneTurn = false
            }
        }else{
            playerTwoMoves.add(id1)
            button.text = "O"
            button.isClickable = false
            if(checkWin(playerTwoMoves)){
                showWinMessage(name2)
                res.performClick()
            }else{
                n2.setTextColor(
                    ContextCompat.getColor(this, R.color.black))
                n2.setTypeface(null, Typeface.NORMAL)
                n1.setTextColor(
                    ContextCompat.getColor(this, R.color.design_default_color_primary_dark))
                n1.setTypeface(null, Typeface.BOLD)
                playerOneTurn = true
            }
        }

        if(count >= 8){
            Toast.makeText(this, "Draw!!", Toast.LENGTH_LONG).show()
            res.performClick()
        }
    }

    private fun checkWin(moves : MutableList<Int>) : Boolean{
        var won = false
        if(moves.size >= 3){
            run loop@{
                possibleWins.forEach {
                    if(moves.containsAll(it)){
                        won = true
                        return@loop
                    }
                }
            }
        }
        return won
    }

    fun showWinMessage(name : TextView){
        var player_name = name.text
        Toast.makeText(this, "Congratulations!! $player_name You Won", Toast.LENGTH_LONG).show()
    }

}