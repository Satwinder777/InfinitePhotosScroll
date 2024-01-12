package com.example.infinitescrollexample.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.infinitescrollexample.R
import com.example.infinitescrollexample.application.MyApp
import com.example.infinitescrollexample.databinding.ActivityRoomBinding
import com.example.infinitescrollexample.room.adapter.RoomAdapter
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
    lateinit var adapter: RoomAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
         name = binding.etname
         mobileno = binding.etmobile
         role = binding.etRole
        db = AppDatabase(applicationContext)


        adapter = RoomAdapter()
        binding.rcroom.adapter =adapter

       db.dao().getAll().observe(this, Observer {

           if (it!=null){
               adapter.submitList(it)
           }else{
               Toast.makeText(this, "room data null ", Toast.LENGTH_SHORT).show()
           }

        })
//        db = MyApp().db()
        //add impl
        binding.addbtn.setOnClickListener {
            insertData()
        }
        //update
        binding.updatebtn.setOnClickListener {
           updateData(mobileno.text.toString().toInt())
        }

        //Read impl
        binding.getdata.setOnClickListener {
//            getDAta()
        }
        binding.deletebtnn.setOnClickListener {
            deleteCall(mobileno.text.toString().toInt())
        }


    }

    private fun deleteCall(id:Int) {

        CoroutineScope(Dispatchers.IO).launch {
            var user = getUser(id)
            if (user!=null){
                db.dao().delete(user)
                Log.e("roomdata123", "getDAta:deleted !! ", )

//                Toast.makeText(this@RoomActivity, "deleted !!", Toast.LENGTH_SHORT).show()

            }else{
                Log.e("roomdata123", "getDAta:deleted null !! ", )

            }

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
            Log.e("roomdata123", "getDAta:inserted !! ", )

//            Toast.makeText(this@RoomActivity, "Inserted !!", Toast.LENGTH_SHORT).show()

        }



    }
    private suspend fun getUser(userId:Int):TodoEntity?{
        var user:TodoEntity?=null
        var job = lifecycleScope.launch {
            if (user==null){
                user = db.dao().getUserById(userId)!!
                Log.e("UserDetails", "getUser: $user", )
            }

        }
        job.join()
        return user
    }

    private fun updateData(id:Int) {
        lifecycleScope.launch(Dispatchers.IO) {
            var user = getUser(id)
            if (user!=null){
                user.name = name.text.toString()
                user.role = role.text.toString()
                db.dao().updateTodo(user)
                Log.e("roomdata123", "getDAta:updated!! $user"  )

//                Toast.makeText(this@RoomActivity, "updated!!", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun getDAta():LiveData<List<TodoEntity>> {
//        var liveList:MutableLiveData<List<TodoEntity>> = MutableLiveData()
//
//        lifecycleScope.launch(Dispatchers.IO) {
//           liveList.postValue(db.dao().getAll().value)
//            Log.e("roomdata123", "getDAta:${liveList.value}", )
//        }
////        db.dao().getAll(
////                TodoEntity(
////                    name = name.text.toString(),
////                    role = role.text.toString(),
////                    mobile = mobileno.text.toString())
////        )
//        return liveList
//    }

}