package com.example.guessnumbergame.utils

import kotlin.random.Random

class GameConstant {
    companion object {
        const val MIN_COUNT_ATTEMPTS: Int = 3
        const val MAX_COUNT_ATTEMPTS: Int = 10

        const val MIN_RANGE_NUMBER: Int = 0
        const val MAX_RANGE_NUMBER: Int = 500

        const val KEY_MY_NUMBER: String = "myNumberGame"

        fun generateAttempts() : Int {
            val random: Random = Random.Default
            return random.nextInt(MIN_COUNT_ATTEMPTS, MAX_COUNT_ATTEMPTS)
        }

        fun generateNumber() : Int {
            val random: Random = Random.Default
            return random.nextInt(MIN_RANGE_NUMBER, MAX_RANGE_NUMBER)
        }

        fun numberEqualRange(number: Int) : Boolean{
            return number in MIN_RANGE_NUMBER..MAX_RANGE_NUMBER
        }
    }

}