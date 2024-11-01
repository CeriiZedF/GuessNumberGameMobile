package com.example.guessnumbergame

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.guessnumbergame.utils.GameConstant
import com.example.guessnumbergame.utils.GameConstant.Companion.KEY_MY_NUMBER


class InputActivity : AppCompatActivity(){
    private lateinit var editTextMyNumber: EditText
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        editTextMyNumber = findViewById(R.id.editTextMyNumber)
        textViewError = findViewById(R.id.textViewError)
    }

    fun onClickInputMyNumber(view: View) {
        if(validateNumber()) {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra(KEY_MY_NUMBER, editTextMyNumber.text.toString())
            startActivity(intent)
            finish()
        }
    }

    fun validateNumber() : Boolean {
        val number = editTextMyNumber.text.toString().toIntOrNull()
        if (number == null) {
            textViewError.text = getString(R.string.field_emptry)
            return false
        }
        if(!GameConstant.numberEqualRange(number)){
            textViewError.text = getString(R.string.number_no_range, GameConstant.MIN_RANGE_NUMBER, GameConstant.MAX_RANGE_NUMBER)
            return false
        }
        return true

    }
}