package com.example.globalfut.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.globalfut.core.model.post.Post
import com.example.globalfut.core.model.post.PostDao
import com.example.globalfut.core.model.postPlayers.PlayerPost
import com.example.globalfut.core.model.postPlayers.PlayerPostDao

@Database(
    version = 3,
    entities = [Post::class, PlayerPost::class]
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun postDao(): PostDao
    abstract fun playerPostDao(): PlayerPostDao

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatabaseHelper::class.java,
                    "globalfut.db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
