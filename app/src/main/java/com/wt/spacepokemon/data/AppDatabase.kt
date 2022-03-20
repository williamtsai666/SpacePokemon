package com.wt.spacepokemon.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 *  author : William Tsai
 *  date : 2022/3/19
 *  description : app database for save pokemon info
 */
@Database(entities = [Pokemon::class], version = 1, exportSchema = false)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
    companion object{
        const val DATABASE_NAME = "pokemon-db"
    }
}