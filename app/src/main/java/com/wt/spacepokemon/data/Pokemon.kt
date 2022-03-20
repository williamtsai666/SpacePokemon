package com.wt.spacepokemon.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey val id: Int,
    val num: String,
    val name: String,
    val img: String,
    val type: List<String>,
    val height: String,
    val weight: String,
    val candy: String,
    val candy_count: Int,
    val egg: String,
    val spawn_chance: Double,
    val avg_spawns: Double,
    val spawn_time: String,
    val weaknesses: List<String>
)