package app.vincent_valdinata.mobiledeveloperintern2024

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextSentence: EditText
    private lateinit var buttonCheck: Button
    private lateinit var buttonNext: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextSentence = findViewById(R.id.editTextSentence)
        buttonCheck = findViewById(R.id.buttonCheck)
        buttonNext = findViewById(R.id.buttonNext)

        buttonCheck.setOnClickListener {
            val sentence = editTextSentence.text.toString()
            if (isPalindrome(sentence)) {
                showMessage("Palindrome")
            } else {
                showMessage("Not Palindrome")
            }
        }

        buttonNext.setOnClickListener {
            val name = editTextName.text.toString()
            val intent = Intent(this, ActivitySecond::class.java)
            intent.putExtra("NAME", name)
            startActivity(intent)
        }
    }

    private fun isPalindrome(sentence: String): Boolean {
        val cleanedSentence = sentence.replace("\\s".toRegex(), "").lowercase()
        return cleanedSentence == cleanedSentence.reversed()
    }

    private fun showMessage(message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }
}