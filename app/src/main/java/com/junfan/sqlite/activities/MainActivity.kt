package com.junfan.sqlite.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.junfan.sqlite.R
import com.junfan.sqlite.adapters.AdapterEmployee
import com.junfan.sqlite.database.DBHelper
import com.junfan.sqlite.models.Employee
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dbHelper: DBHelper
    lateinit var list: ArrayList<Employee>
    lateinit var adapterEmployee: AdapterEmployee

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)
        list = dbHelper.getAllEmployees()

        init()
    }

    private fun init() {
        button_create_main.setOnClickListener(this)
        button_search_main.setOnClickListener(this)
        adapterEmployee = AdapterEmployee(this, list)
        recycler_view.adapter = adapterEmployee
        recycler_view.layoutManager = LinearLayoutManager(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            button_create_main -> startActivity(Intent(this, CreateActivity::class.java))
            button_search_main -> {
                var id = edit_text_search.text.toString().toInt()
                var employee = dbHelper.getEmployee(id)
                var intent = Intent(this, SearchActivity::class.java)
                if(list.contains(employee)) {
                    intent.putExtra("employee", employee)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext, "Not found", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}