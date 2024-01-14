package com.example.infinitescrollexample.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.infinitescrollexample.room.entity.TodoEntity

@Dao
interface TodoDao {

    @Query("SELECT * FROM emp_data")
    fun getAll(): LiveData<List<TodoEntity>>
    @Query("SELECT * FROM emp_data WHERE id = :userId")
    suspend fun getUserById(userId: Int): TodoEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( todo: TodoEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todos: TodoEntity)

}