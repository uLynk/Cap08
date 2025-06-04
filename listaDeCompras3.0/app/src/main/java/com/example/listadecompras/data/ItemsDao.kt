package com.example.listadecompras.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemsDao {

    @Insert
    suspend fun insert(item: ItemEntity): Long

    @Delete
    suspend fun delete(item: ItemEntity): Int

    @Query("SELECT * FROM ItemEntity")
    suspend fun getAll(): List<ItemEntity>
}