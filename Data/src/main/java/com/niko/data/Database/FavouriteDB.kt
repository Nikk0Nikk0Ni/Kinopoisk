package com.niko.data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database()
abstract class FavouriteDB : RoomDatabase() {
    companion object{
        fun getDB(context : Context) : FavouriteDB{
            return Room.databaseBuilder(context,
                FavouriteDB::class.java,
                "Favoutite DB").build()
        }
    }
}