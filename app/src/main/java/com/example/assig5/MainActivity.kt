package com.example.assig5

import android.app.DownloadManager
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.example.assig5.adapter.TodoAdapter
import com.example.assig5.model.ToDo
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var progressDialog: ProgressDialog
    lateinit var todoAdapter: TodoAdapter
    lateinit var data: ArrayList<ToDo>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading ... ")
        progressDialog.setCancelable(false)

        data = ArrayList()

        getToDo()

    }


    fun getToDo() {
        progressDialog.show()
        val StringRequest = StringRequest(Request.Method.GET, URLs.GET_TODO, Response.Listener { response ->
            progressDialog.dismiss()

            val array = JSONArray(response)

            for (i in 0 until 20) {
                data.add(
                        ToDo(
                                array.getJSONObject(i).getInt("id"),
                                array.getJSONObject(i).getString("title"),
                                array.getJSONObject(i).getBoolean("completed")
                        )
                )
            }

            todoAdapter = TodoAdapter(this,data)
            recyData.layoutManager = LinearLayoutManager(this)
            recyData.adapter = todoAdapter


        }, Response.ErrorListener { error ->

        })

        VolleySingleton.getInstance((this)).addToRequestQueue(StringRequest)
    }

}