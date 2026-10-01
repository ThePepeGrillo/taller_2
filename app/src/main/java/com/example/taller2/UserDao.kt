package com.example.taller2
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface UserDao {
    // 'suspend' hace que la función sea asíncrona (usa Coroutines)
    @Insert
    suspend fun insertUser(user: User)

    // Devuelve un Flow, que emitirá la lista de usuarios cada vez que cambie.
    // La UI reaccionará a estos cambios automáticamente.
    @Query("SELECT * FROM users ORDER BY name ASC")
    fun getAllUsers(): Flow<List<User>>
}