package com.junfan.sqlite.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.junfan.sqlite.R
import com.junfan.sqlite.activities.DeleteActivity
import com.junfan.sqlite.activities.SearchActivity
import com.junfan.sqlite.activities.UpdateActivity
import com.junfan.sqlite.models.Employee
import kotlinx.android.synthetic.main.row_employee_adapter.view.*

class AdapterEmployee(var mContext:Context, var mList: ArrayList<Employee>) : RecyclerView.Adapter<AdapterEmployee.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var view = LayoutInflater.from(mContext).inflate(R.layout.row_employee_adapter, parent, false)
        var myViewHolder = MyViewHolder(view)
        return myViewHolder
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var employee = mList[position]
        holder.bind(employee)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(employee: Employee) {

            itemView.recycler_view_name.text = employee.name
            itemView.recycler_view_id.text = employee.id.toString()

            itemView.recycler_view_edit.setOnClickListener {
                var intent = Intent(mContext, UpdateActivity::class.java)
                intent.putExtra("employee", employee)
                mContext.startActivity(intent)
            }

            itemView.recycler_view_delete.setOnClickListener {
                var intent = Intent(mContext, DeleteActivity::class.java)
                intent.putExtra("employee", employee)
                mContext.startActivity(intent)
            }

            itemView.setOnClickListener {
                var intent = Intent(mContext, SearchActivity::class.java)
                intent.putExtra("employee", employee)
                mContext.startActivity(intent)
            }

        }
    }
}