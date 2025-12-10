package com.example.globalfut.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.globalfut.core.model.post.Post
import com.example.globalfut.core.model.post.Banner
import com.example.globalfut.core.model.postPlayers.PlayerPost
import com.example.globalfut.core.model.postPlayers.PlayerPostDao
import com.example.globalfut.core.model.teams.Teams
import com.example.globalfut.core.model.teams.TeamsDao

@Database(
    version = 5,
    entities = [Post::class, PlayerPost::class, Teams::class, Banner::class]
)
abstract class DatabaseHelper : RoomDatabase() {

    abstract fun postDao(): LocalHomeDataSource
    abstract fun playerPostDao(): PlayerPostDao
    abstract fun teamsDao(): TeamsDao

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
