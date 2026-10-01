package com.example.taller2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        // La anotación @Volatile asegura que la instancia sea siempre la más actualizada

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            // synchronized asegura que solo un hilo pueda crear la instancia a la vez.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "user_database" // Nombre del archivo de la base de datos
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}