package com.junfan.sqlite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.junfan.sqlite.R
import com.junfan.sqlite.database.DBHelper
import com.junfan.sqlite.models.Employee
import kotlinx.android.synthetic.main.activity_delete.*

class DeleteActivity : AppCompatActivity() {

    lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        dbHelper = DBHelper(this)

        init()
    }

    private fun init() {
        var employee = intent.getSerializableExtra("employee") as Employee
        var id = employee.id
        text_view_id_delete.text = employee.id.toString()
        text_view_name_delete.text = employee.name
        text_view_email_delete.text = employee.email

        button_delete_delete.setOnClickListener {
            dbHelper.deleteEmployee(id)
            Toast.makeText(applicationContext, "Sucessfully Deleted", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}