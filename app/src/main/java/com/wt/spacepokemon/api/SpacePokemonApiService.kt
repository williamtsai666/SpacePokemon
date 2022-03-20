package com.wt.spacepokemon.api

import com.google.gson.Gson
import com.wt.spacepokemon.data.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : api service with load data method
 */
class SpacePokemonApiService {
    private val okhttpLogger =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(okhttpLogger)
        .build()

    private val gson = Gson()

    init {
        println("init SpacePokemonApiService")
    }

    private suspend fun httpGetSync(url: String): Response? {
        try {
            val requestBuilder = Request.Builder()
            requestBuilder.url(url)
            val request = requestBuilder.build()
            return okHttpClient.newCall(request).execute()
        } catch (e: Exception) {
        }
        return null
    }

    /**
     * fetch pokemon list from web
     */
    suspend fun fetchPokemonListData(): List<Pokemon>? {
        val response = httpGetSync(URL_POKEMON_LIST)
        response?.run {
            if (response.isSuccessful) {
                val pokemonList =
                    gson.fromJson(response.body?.string(), PokemonListResponse::class.java)
                return pokemonList.pokemon
            }
        }
        return null
    }


    /**
     * fetch  pokemon list and craft info
     */
    suspend fun fetchSpacePokemonData(): List<PokemonInSpace>? {
        val response = httpGetSync(URL_POKEMON_IN_SPACE)
        response?.run {
            if (response.isSuccessful) {
                val spaceResponse = gson.fromJson(response.body?.string(), SpaceResponse::class.java)
                return spaceResponse.space
            }
        }
        return null
    }


    companion object {
        const val URL_POKEMON_IN_SPACE =
            "https://gist.githubusercontent.com/kamalh16/45b2fd26a8657f9bd97222b74bdce6ec/raw/c11831409641729a2da5347522ff7eff00ae2d0e/pokemon_in_space.json"
        const val URL_POKEMON_LIST =
            "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json"
    }
}