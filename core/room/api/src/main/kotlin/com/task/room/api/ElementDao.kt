package com.task.room.api

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.task.room.entities.PROFILE_ELEMENTS
import com.task.room.entities.ProfileElementEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ElementDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insert(element: ProfileElementEntity)

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAll(elements: List<ProfileElementEntity>)

    @Query("SELECT * FROM $PROFILE_ELEMENTS")
    fun selectAllFlow(): Flow<List<ProfileElementEntity>>

    @Query("SELECT * FROM $PROFILE_ELEMENTS")
    suspend fun selectAll(): List<ProfileElementEntity>

    @Query("SELECT * FROM $PROFILE_ELEMENTS WHERE id=:id LIMIT 1")
    suspend fun selectById(id: String): ProfileElementEntity

    @Update
    suspend fun update(element: ProfileElementEntity)

    @Update
    suspend fun update(elements: List<ProfileElementEntity>)

    @Query("DELETE FROM $PROFILE_ELEMENTS WHERE id=:id ")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM $PROFILE_ELEMENTS")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(elements: List<ProfileElementEntity>)
}