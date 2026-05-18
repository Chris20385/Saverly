package com.example.saverly

import org.junit.Assert.assertEquals
import org.junit.Test

class SaverlyUnitTest {

    @Test
    fun testCalculateTotalSpending() {

        val result = calculateTotalSpending(30, 20, 40)

        assertEquals(90, result)
    }
}