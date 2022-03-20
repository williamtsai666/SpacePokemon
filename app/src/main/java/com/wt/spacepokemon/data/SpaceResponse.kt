package com.wt.spacepokemon.data

data class SpaceResponse(
    val space: List<PokemonInSpace>
)

data class PokemonInSpace(
    val craft: String,
    val id: Int,
    val name: String,
    val num: String,
    var imgUrl: String
)