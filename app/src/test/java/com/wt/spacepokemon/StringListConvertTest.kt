package com.wt.spacepokemon

import com.wt.spacepokemon.data.StringListConverter
import org.junit.Assert.assertEquals
import org.junit.Test

class StringListConvertTest {

    private val strList = mutableListOf<String>("a", "b", "c", "d")
    private val strWithComma = "a,b,c,d"

    @Test
    fun testStringToList() {
        assertEquals(strWithComma, StringListConverter().toString(strList))
    }

    @Test
    fun testListToString() {
        assertEquals(strList, StringListConverter().fromString(strWithComma))
    }
}