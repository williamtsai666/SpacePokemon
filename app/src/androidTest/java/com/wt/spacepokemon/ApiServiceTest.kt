package com.wt.spacepokemon

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.wt.spacepokemon.api.SpacePokemonApiService
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ApiServiceTest {

    @Test
    fun testFetchPokemonList() {
        //run with coroutine
        runBlocking {
            val fetchPokemonListData = SpacePokemonApiService().fetchPokemonListData()
            assert(fetchPokemonListData?.size!! > 0)
        }
    }

    @Test
    fun testFetchCraftList() {
        //run with coroutine
        runBlocking {
            val fetchSpacePokemonData = SpacePokemonApiService().fetchSpacePokemonData()
            assert(fetchSpacePokemonData?.get(0)!!.craft.isNotEmpty())
        }
    }
}