package com.junfan.sqlite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.junfan.sqlite.R
import com.junfan.sqlite.database.DBHelper
import com.junfan.sqlite.models.Employee
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        dbHelper = DBHelper(this)

        init()
    }

    private fun init() {
        button_create_create.setOnClickListener {
            var id = edit_text_id_create.text.toString().toInt()
            var name = edit_text_name_create.text.toString()
            var email = edit_text_email_create.text.toString()

            var employee = Employee(id, name, email)

            dbHelper.addEmployee(employee)

            Toast.makeText(applicationContext, "successfully Created Employee", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}