package com.junfan.sqlite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.junfan.sqlite.R
import com.junfan.sqlite.models.Employee
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        init()
    }

    private fun init() {

        var employee = intent.getSerializableExtra("employee") as Employee

        text_view_id_search.text = employee.id.toString()
        text_view_name_search.text = employee.name
        text_view_email_search.text = employee.email

        button_back_search.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}