package com.junfan.sqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.junfan.sqlite.models.Employee

class DBHelper(var mContext: Context): SQLiteOpenHelper(mContext, DATABASE_NAME, null,
    DATABASE_VERSION) {

    var db = writableDatabase

    companion object {
        const val DATABASE_NAME = "empDB"
        const val DATABASE_VERSION = 2
        const val TABLE_NAME = "employee"
        const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "create table $TABLE_NAME ($COLUMN_ID integer, $COLUMN_NAME char(50), $COLUMN_EMAIL varchar(200))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTable = "drop table $TABLE_NAME"
        db?.execSQL(dropTable)
        onCreate(db)
    }

    fun addEmployee(employee: Employee) {
        var contentValue = ContentValues()
        contentValue.put(COLUMN_ID, employee.id)
        contentValue.put(COLUMN_NAME, employee.name)
        contentValue.put(COLUMN_EMAIL, employee.email)
        db.insert(TABLE_NAME, null, contentValue)
        Log.d("abc", "add Employee")
    }

    fun updateEmployee(employee: Employee): Int {
        var contentValue = ContentValues()
        contentValue.put(COLUMN_NAME,employee.name)
        contentValue.put(COLUMN_EMAIL, employee.email)
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(employee.id.toString())
        return db.update(TABLE_NAME, contentValue, whereClause, whereArgs)
    }

    fun deleteEmployee(id: Int): Int {
        var whereClause = "$COLUMN_ID = ?"
        var whereArgs = arrayOf(id.toString())
        return db.delete(TABLE_NAME, whereClause, whereArgs)
    }

    fun getEmployee(id: Int): Employee? {
        var employee: Employee? = null
        var query = "select * from $TABLE_NAME where $COLUMN_ID = ?"
        var cursor = db.rawQuery(query, arrayOf(id.toString()))
        if (cursor != null && cursor.moveToFirst()) {
            do {
                var e_id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                var name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                var email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))
                employee = Employee(e_id, name, email)
            }while (cursor.moveToNext())

        }
        return employee
    }


    //select * from employee
    fun getAllEmployees(): ArrayList<Employee> {
        var list: ArrayList<Employee> = ArrayList()
        var columns = arrayOf(
            COLUMN_ID,
            COLUMN_NAME,
            COLUMN_EMAIL
        )
        var cursor = db.query(TABLE_NAME, columns, null, null, null, null, null)
        if(cursor != null && cursor.moveToFirst()) {
            do {
                var id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                var name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                var email = cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL))

                var employee = Employee(id, name, email)
                list.add(employee)

            }while(cursor.moveToNext())
        }
        return list
    }

}