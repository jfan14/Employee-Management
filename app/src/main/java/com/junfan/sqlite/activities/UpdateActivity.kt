package com.junfan.sqlite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.junfan.sqlite.R
import com.junfan.sqlite.database.DBHelper
import com.junfan.sqlite.models.Employee
import kotlinx.android.synthetic.main.activity_update.*

class UpdateActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        dbHelper = DBHelper(this)

        init()
    }

    private fun init() {

        var employee1 = intent.getSerializableExtra("employee") as Employee
        text_view_id_update.text = employee1.id.toString()

        button_update_update.setOnClickListener {
            var name = edit_text_name_update.text.toString()
            var email = edit_text_email_update.text.toString()

            var employee = Employee(employee1.id, name, email)

            dbHelper.updateEmployee(employee)

            Toast.makeText(applicationContext, "Update Successfully", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}