package com.example.quotesmadude

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var quoteTextView: TextView
    private lateinit var copyButton: View
    private lateinit var refreshButton: View

    private val quotes = mapOf(
        "angry" to listOf(
            "Anger is a wind which blows out the lamp of the mind.",
            "Speak when you are angry and you will make the best speech you will ever regret.",
            "The greatest remedy for anger is delay.",
            "Never do anything when you are in a temper, for you will do everything wrong.",
            "Holding onto anger is like grasping a hot coal with the intent of throwing it at someone else; you are the one who gets burned."
        ),
        "sad" to listOf(
            "Tears are words that need to be written.",
            "Every man has his secret sorrows which the world knows not; and often times we call a man cold when he is only sad.",
            "The word ‘happy’ would lose its meaning if it were not balanced by sadness.",
            "Sadness flies away on the wings of time.",
            "The pain never really goes away; you just elevate and get used to it by growing stronger."
        ),
        "in_love" to listOf(
            "Love is composed of a single soul inhabiting two bodies.",
            "Love is a friendship set to music.",
            "The best thing to hold onto in life is each other.",
            "To love and be loved is to feel the sun from both sides.",
            "In the end, the love you take is equal to the love you make."
        ),
        "confused" to listOf(
            "In the middle of chaos lies opportunity.",
            "Confusion is the welcome mat at the door of creativity.",
            "The man who moves a mountain begins by carrying away small stones.",
            "The road to enlightenment is long and difficult, and you should try not to forget snacks and magazines.",
            "Not all those who wander are lost."
        ),
        "tired" to listOf(
            "Fatigue is the best pillow.",
            "The best cure for the body is a quiet mind.",
            "To rest is not a defeat, but a strategic pause.",
            "Sometimes the most productive thing you can do is rest.",
            "In an exhausted mind, there is no room for creativity."
        ),
        "happy" to listOf(
            "Happiness is a direction, not a place.",
            "The most simple things can bring the most happiness.",
            "For every minute you are angry, you lose sixty seconds of happiness.",
            "Happiness depends upon ourselves.",
            "A joyful heart is the inevitable result of a heart burning with love."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        quoteTextView = findViewById(R.id.quoteTextView)
        copyButton = findViewById(R.id.copyButton)
        refreshButton = findViewById(R.id.refreshButton)

        val emojiButtons = mapOf(
            "angry" to findViewById<ImageButton>(R.id.imageButton6),
            "sad" to findViewById<ImageButton>(R.id.imageButton7),
            "in_love" to findViewById<ImageButton>(R.id.imageButton8),
            "confused" to findViewById<ImageButton>(R.id.imageButton9),
            "tired" to findViewById<ImageButton>(R.id.imageButton10),
            "happy" to findViewById<ImageButton>(R.id.imageButton11)
        )

        emojiButtons.forEach { (emotion, button) ->
            button.setOnClickListener {
                displayQuote(emotion)
            }
        }

        copyButton.setOnClickListener {
            copyQuoteToClipboard()
        }

        refreshButton.setOnClickListener {
            clearQuote()
        }
    }

    private fun displayQuote(emotion: String) {
        val quotesList = quotes[emotion]
        if (quotesList != null) {
            val randomQuote = quotesList[Random.nextInt(quotesList.size)]
            quoteTextView.text = randomQuote
            quoteTextView.visibility = View.VISIBLE
            copyButton.visibility = View.VISIBLE
            refreshButton.visibility = View.VISIBLE
        }
    }

    private fun copyQuoteToClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("quote", quoteTextView.text)
        clipboard.setPrimaryClip(clip)
        Toast.makeText(this, "Quote copied to clipboard!", Toast.LENGTH_SHORT).show()
    }

    private fun clearQuote() {
        quoteTextView.text = ""
        quoteTextView.visibility = View.GONE
        copyButton.visibility = View.GONE
        refreshButton.visibility = View.GONE
    }
}
