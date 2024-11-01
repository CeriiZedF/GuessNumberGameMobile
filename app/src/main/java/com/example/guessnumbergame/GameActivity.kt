package com.example.guessnumbergame

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.isDigitsOnly
import com.example.guessnumbergame.utils.GameConstant
import com.example.guessnumbergame.utils.GameConstant.Companion.KEY_MY_NUMBER

class GameActivity : AppCompatActivity() {
    private var countAttempts: Int = 0;
    private var randomNumber: Int = 0;
    private var winner: Boolean = false;

    private lateinit var infoTextView: TextView
    private lateinit var numberEditText: EditText
    private lateinit var tipTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var inputButton: Button
    private lateinit var resetButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initializeElement()
        start()
    }

    fun swapInputResetButton(flag : Boolean) {
        if(flag) {
            inputButton.visibility = View.GONE
            resetButton.visibility = View.VISIBLE
        }
        else {
            inputButton.visibility = View.VISIBLE
            resetButton.visibility = View.GONE
        }

    }

    fun initializeElement() {
        infoTextView = findViewById(R.id.infoTextView)
        numberEditText = findViewById(R.id.numberEditText)
        tipTextView = findViewById(R.id.tipTextView)
        resultTextView = findViewById(R.id.resultTextView)
        inputButton = findViewById(R.id.inputButton)
        resetButton = findViewById(R.id.resetButton)
    }

    fun start() {
        val number = intent.getStringExtra(KEY_MY_NUMBER)?.toInt()
        if(number == null){
            randomNumber = GameConstant.generateNumber()
        }
        else{
            randomNumber = number
        }
        winner = false
        resultTextView.text = ""
        countAttempts = GameConstant.generateAttempts()
        Log.i("Game", randomNumber.toString())
        setInfoCountAttempts()
    }

    fun setInfoCountAttempts(){
        infoTextView.text = getString(R.string.count_attemts, countAttempts)

    }

    private fun validateInput(input: String) : Boolean {
        when {
            input.isEmpty() -> {
                resultTextView.setText("Ошибка: Поле пустое")
                Log.e("Game", "Ошибка: Поле пустое")
                return false
            }
            !input.isDigitsOnly() -> {
                resultTextView.setText("Ошибка: В поле есть буквы")
                Log.e("Game", "Ошибка: В поле есть буквы")
                return false
            }
            else -> {
                Log.i("Game", "Введенное число подходит")
                return true
            }
        }
    }

    fun onClickInputNumber(view: View) {
        if(validateInput(numberEditText.text.toString())){
            logicGame()
        }
    }

    fun onClickResetGame(view: View) {
        swapInputResetButton(false)
        start()
    }

    fun endGame() {
        swapInputResetButton(true)
        if(winner) {
            resultTextView.text = getString(R.string.winner)
            resetButton.text = getString(R.string.winner_reset_text)
        } else {
            resultTextView.setText(getString(R.string.lose, randomNumber))
            resetButton.text = getString(R.string.lose_reset_text)
        }
    }


    fun logicGame(){
        var number = Integer.parseInt(numberEditText.text.toString())
        when {
            randomNumber < number -> {
                countAttempts--;
                Log.i("Game", "Value < Random")
                tipTextView.text = getString(R.string.number_less)
            }
            randomNumber > number -> {
                countAttempts--;
                Log.i("Game", "Value > Random")
                tipTextView.text = getString(R.string.number_greater)
            }
            else -> {
                Log.i("Game", "Value == Random")
                tipTextView.text = getString(R.string.number_equal)
                winner = true
            }
        }
        setInfoCountAttempts()

        if(winner || countAttempts <= 0){
            endGame()
            return
        }
    }
}