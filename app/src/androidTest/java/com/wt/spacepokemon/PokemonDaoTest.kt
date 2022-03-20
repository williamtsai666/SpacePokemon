package com.wt.spacepokemon

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.wt.spacepokemon.data.AppDatabase
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PokemonDaoTest {

    @Test
    fun testGetPokemon() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, AppDatabase.DATABASE_NAME
        ).build()

        val pokemonDao = db.pokemonDao()
        assert(pokemonDao.findPokemonById(1).id == 1)
        assert(pokemonDao.findPokemonImgUrlById(1).isNotEmpty())
    }
}