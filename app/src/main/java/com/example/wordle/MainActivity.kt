package com.example.wordle

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var wordToGuess = FourLetterWordList.getRandomFourLetterWord()
    var count = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //guessButton, reset button, answerBox views
        val button = findViewById<Button>(R.id.guessButton)
        val reset = findViewById<Button>(R.id.resetButton)
        val answer = findViewById<TextView>(R.id.resultTextView)


        /*check the guess using the checkGuess function, store result in variable. put result in
        guess#ResultView*/
        button.setOnClickListener {

            when (count) {
                1 -> {

                    //hide keyboard
                    hideKeyboard()

                    //retrieving value from the user entered in the guessEditText
                    var guessVal = findViewById<EditText>(R.id.guessEditText)
                    var guess: String = guessVal.text.toString()
                    guess = guess.uppercase()

                    //see if they guessed correct word
                    if (wordToGuess == guess) {
                        val victory = findViewById<TextView>(R.id.victoryView)
                        victory.visibility = View.VISIBLE
                        reset.visibility = View.VISIBLE
                        answer.visibility = View.VISIBLE
                        button.isClickable = false
                        button.alpha = .5f
                    }

                    //result, displayed as (X0+X)
                    var result = checkGuess(guess)

                    //textViews
                    var guessCountView = findViewById<TextView>(R.id.guess1View)
                    var responseView = findViewById<TextView>(R.id.guess1ResponseView)
                    var checkView = findViewById<TextView>(R.id.guess1CheckView)
                    var resultView = findViewById<TextView>(R.id.guess1ResultView)

                    //make all boxes visible
                    guessCountView.visibility = View.VISIBLE
                    responseView.visibility = View.VISIBLE
                    checkView.visibility = View.VISIBLE
                    resultView.visibility = View.VISIBLE
                    responseView.text = guess.toString()
                    resultView.text = result.toString()

                    //increment guessCount
                    count++

                    //guess 2
                }
                2 -> {

                    //hide keyboard
                    hideKeyboard()

                    //retrieving value from the user entered in the guessEditText
                    var guessVal = findViewById<EditText>(R.id.guessEditText)
                    var guess: String = guessVal.text.toString()
                    guess = guess.uppercase()

                    //see if they guessed correct word
                    if (wordToGuess == guess) {
                        val victory = findViewById<TextView>(R.id.victoryView)
                        victory.visibility = View.VISIBLE
                        reset.visibility = View.VISIBLE
                        button.isClickable = false
                        button.alpha = .5f
                        answer.visibility = View.VISIBLE
                    }

                    //result, displayed as (X0+X)
                    var result = checkGuess(guess)

                    //textViews
                    var guessCountView = findViewById<TextView>(R.id.guess2View)
                    var responseView = findViewById<TextView>(R.id.guess2ResponseView)
                    var checkView = findViewById<TextView>(R.id.guess2CheckView)
                    var resultView = findViewById<TextView>(R.id.guess2ResultView)

                    //make all boxes visible
                    guessCountView.visibility = View.VISIBLE
                    responseView.visibility = View.VISIBLE
                    checkView.visibility = View.VISIBLE
                    resultView.visibility = View.VISIBLE
                    responseView.text = guess.toString()
                    resultView.text = result.toString()

                    //increment guessCount
                    count++

                    //guess 3
                }
                3 -> {

                    //hide keyboard
                    hideKeyboard()

                    //retrieving value from the user entered in the guessEditText
                    var guessVal = findViewById<EditText>(R.id.guessEditText)
                    var guess: String = guessVal.text.toString()
                    guess = guess.uppercase()

                    //see if they guessed correct word
                    if (wordToGuess == guess) {
                        val victory = findViewById<TextView>(R.id.victoryView)
                        victory.visibility = View.VISIBLE
                        reset.visibility = View.VISIBLE
                        button.isClickable = false
                        button.alpha = .5f
                        answer.visibility = View.VISIBLE
                    } else {
                        Toast.makeText(it.context, "Ran out of guesses!", Toast.LENGTH_LONG).show()
                        button.isClickable = false
                        button.alpha = .5f
                        reset.visibility = View.VISIBLE
                        reset.setOnClickListener{

                        }
                    }

                    //result, displayed as (X0+X)
                    var result = checkGuess(guess)

                    //textViews
                    var guessCountView = findViewById<TextView>(R.id.guess3View)
                    var responseView = findViewById<TextView>(R.id.guess3ResponseView)
                    var checkView = findViewById<TextView>(R.id.guess3CheckView)
                    var resultView = findViewById<TextView>(R.id.guess3ResultView)

                    //make all boxes visible
                    guessCountView.visibility = View.VISIBLE
                    responseView.visibility = View.VISIBLE
                    checkView.visibility = View.VISIBLE
                    resultView.visibility = View.VISIBLE
                    answer.visibility = View.VISIBLE
                    answer.text = wordToGuess.toString()
                    responseView.text = guess.toString()
                    resultView.text = result.toString()

                }
            }
        }
    }

    private fun checkGuess(guess: String): String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            } else if (guess[i] in wordToGuess) {
                result += "+"
            } else {
                result += "X"
            }
        }
        return result
    }

    fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
