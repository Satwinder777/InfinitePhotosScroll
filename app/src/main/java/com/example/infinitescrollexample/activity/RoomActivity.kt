package com.example.infinitescrollexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.infinitescrollexample.R
import com.example.infinitescrollexample.application.MyApp
import com.example.infinitescrollexample.databinding.ActivityRoomBinding
import com.example.infinitescrollexample.room.db.AppDatabase
import com.example.infinitescrollexample.room.entity.TodoEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.launch

class RoomActivity : AppCompatActivity() {
    lateinit var binding :ActivityRoomBinding
    lateinit var db :AppDatabase
    lateinit var name:EditText
    lateinit var mobileno:EditText
    lateinit var role:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
         name = binding.etname
         mobileno = binding.etmobile
         role = binding.etRole
        db = AppDatabase(applicationContext)

//        db = MyApp().db()
        //add impl
        binding.addbtn.setOnClickListener {
            insertData()
        }
        //update
        binding.updatebtn.setOnClickListener {
           updateData()
        }

        //Read impl
        binding.getdata.setOnClickListener {
            getDAta()
        }
        binding.deletebtnn.setOnClickListener {
            deleteCall()
        }


    }

    private fun deleteCall() {
        CoroutineScope(Dispatchers.IO).launch {
            db.dao().delete(
                TodoEntity(
                    id = mobileno.text.toString().toInt(),
                    name = name.text.toString(),
                    role = role.text.toString(),
                    mobile = mobileno.text.toString())
            )
        }

    }

    private fun insertData() {
        CoroutineScope(Dispatchers.IO).launch{
            db.dao().insert(
                TodoEntity(
                    id = null,
                    name = name.text.toString(),
                    role = role.text.toString(),
                    mobile = mobileno.text.toString())
            )
        }



    }

    private fun updateData() {
        lifecycleScope.launch(Dispatchers.IO) {
            db.dao().updateTodo(
                TodoEntity(
                    id = null,
                    name = name.text.toString(),
                    role = role.text.toString(),
                    mobile = mobileno.text.toString()
                )
            )
        }
    }

    private fun getDAta() {
        lifecycleScope.launch(Dispatchers.IO) {
            var data = db.dao().getAll()
            Log.e("roomdata123", "getDAta:$data ", )
        }
//        db.dao().getAll(
//                TodoEntity(
//                    name = name.text.toString(),
//                    role = role.text.toString(),
//                    mobile = mobileno.text.toString())
//        )
    }

}