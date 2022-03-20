package com.wt.spacepokemon.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemonList: List<Pokemon>)

    @Query("SELECT * FROM pokemon WHERE id=:id")
    suspend fun findPokemonById(id: Int): Pokemon

    @Query("SELECT img FROM pokemon WHERE id=:id")
    suspend fun findPokemonImgUrlById(id: Int): String
}