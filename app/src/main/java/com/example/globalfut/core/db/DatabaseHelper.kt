package com.example.globalfut.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.globalfut.core.model.post.PostDao
import com.example.globalfut.core.model.post.Post

@Database(
    version = 1, entities = [Post::class]
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun postDao() : PostDao

    companion object {
        fun getInstance(context: Context): DatabaseHelper {
            return Room.databaseBuilder(
                context,
                DatabaseHelper::class.java,
                "globalfut.db"
            ).build()
        }

    }
}