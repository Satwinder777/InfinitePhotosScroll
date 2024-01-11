package com.example.infinitescrollexample.room.dao

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
    suspend fun getAll(): List<TodoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert( todo: TodoEntity)

    @Delete
    suspend fun delete(todo: TodoEntity)

    @Update
    suspend fun updateTodo(todos: TodoEntity)

}