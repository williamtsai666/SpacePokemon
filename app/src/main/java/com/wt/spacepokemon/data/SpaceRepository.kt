package com.wt.spacepokemon.data

import com.wt.spacepokemon.api.SpacePokemonApiService
import javax.inject.Inject

/**
 *  author : William Tsai
 *  date : 2022/3/17
 *  description : method to get data
 */
class SpaceRepository @Inject constructor(
    private val appDatabase: AppDatabase,
    private val apiService: SpacePokemonApiService
) {
    private val craftToPokemonListMap = mutableMapOf<String, MutableList<PokemonInSpace>>()

    init {
        println("init SpaceRepository")
    }

    /**
     * load pokemon in space and group by craft
     */
    suspend fun fetchSpaceData(): MutableList<Craft> {
        val pokemonSpaceList = apiService.fetchSpacePokemonData()
        //group list by craft
        val groupByResult = pokemonSpaceList?.groupBy(keySelector = { it.craft })
        //sort craft by num
        groupByResult?.run {
            for (key in keys) {
                //sort pokemon in craft by num and save to map
                val list = groupByResult[key]?.sortedBy { it.num }
                craftToPokemonListMap[key] = list as MutableList<PokemonInSpace>
            }
        }

        //create craft list for recyclerview to show
        val craftList = mutableListOf<Craft>()
        val craftKeys = craftToPokemonListMap.keys
        for (craft in craftKeys) {
            val pokemonList = craftToPokemonListMap[craft]
            craftList.add(Craft(craft))
        }
        return craftList
    }

    /**
     * fetch pokemon info and save to database in order that
     * detail page can query pokemon info via db
     */
    suspend fun fetchPokemonListAndSaveIntoDB() {
        println("start fet pokemon list")
        val start = System.currentTimeMillis()
        val pokemonList = apiService.fetchPokemonListData()
        val mid = System.currentTimeMillis()
        println("fetch pokemon list cost ${mid - start} ms")
        pokemonList?.let {
            println("start save pokemon list to db")
            appDatabase.pokemonDao().insertAll(it)
            val end = System.currentTimeMillis()
            println("save  pokemon list cost ${end - mid} ms")
        }
    }

    /**
     * get pokemon list by craft
     */
    fun getPokemonListByCraftName(craftName: String): MutableList<PokemonInSpace>? {
        return craftToPokemonListMap[craftName]
    }

    /**
     * query db to get pokemon img
     */
    suspend fun findPokemonImgUrlById(id: Int): String {
        return appDatabase.pokemonDao().findPokemonImgUrlById(id)
    }

    /**
     * query db to get pokemon info
     */
    suspend fun findPokemonById(id: Int): Pokemon {
        return appDatabase.pokemonDao().findPokemonById(id)
    }


}