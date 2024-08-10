package app.vincent_valdinata.mobiledeveloperintern2024

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ActivitySecond : AppCompatActivity() {

    private lateinit var selectedUserNameTextView: TextView
    private lateinit var chosenUserTextView: TextView
    private val REQUEST_CODE_SELECT_USER = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        selectedUserNameTextView = findViewById(R.id.selectedUserNameTextView)
        chosenUserTextView = findViewById(R.id.chosenUserTextView)

        val name = intent.getStringExtra("NAME")
        chosenUserTextView.text = name

        val chooseUserButton: Button = findViewById(R.id.chooseUserButton)
        chooseUserButton.setOnClickListener {
            val intent = Intent(this, UserListActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SELECT_USER)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_USER && resultCode == Activity.RESULT_OK) {
            val selectedUserName = data?.getStringExtra("SELECTED_USER_NAME")
            selectedUserNameTextView.text = selectedUserName
        }
    }
}
