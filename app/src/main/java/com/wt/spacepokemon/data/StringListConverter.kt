package com.wt.spacepokemon.data

import androidx.room.TypeConverter

/**
 *  author : William Tsai
 *  date : 2022/3/18
 *  description : converter for room for save list
 */
class StringListConverter {

    @TypeConverter
    fun fromString(stringListString: String): List<String> {
        return stringListString.split(",").map { it }
    }

    @TypeConverter
    fun toString(stringList: List<String>): String {
        return stringList.joinToString(separator = ",")
    }
}