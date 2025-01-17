/*
 * Copyright (c) 2025 Matthew Nelson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package org.kotlincrypto.bitops.bits

import org.kotlincrypto.bitops.endian.Endian.Big.beLongAt
import java.math.BigInteger
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Uses Java's [BigInteger] to verify [Counter.Bit64.Final.asBits] functions as expected.
 * */
class CounterJvmUnitTest {

    @Test
    fun givenBit64Final_whenAsMaxBits_thenConvertsAsExpected() {
        val max = BigInteger("2").pow(128)
        val maxBytes = max.divide(BigInteger("8"))
        val maxBytesMinus1 = maxBytes.minus(BigInteger.ONE)
        val expectedBits = maxBytesMinus1.times(BigInteger("8"))

        val bMaxBytesMinus1 = maxBytesMinus1.toByteArray()
        val bExpectedBits = expectedBits.toByteArray()

        val bits = Counter.Bit64.Final(
            lo = bMaxBytesMinus1.beLongAt(8),
            hi = bMaxBytesMinus1.beLongAt(0),
        ).asBits()

        // BigInteger.toByteArray returns 17 bytes, prefixed with
        // a leading 0 which is dropped.
        val expectedLo = bExpectedBits.beLongAt(9)
        val expectedHi = bExpectedBits.beLongAt(1)

        assertEquals(-8L, expectedLo)
        assertEquals(-1L, expectedHi)

        assertEquals(expectedLo, bits.lo)
        assertEquals(expectedHi, bits.hi)
    }

    @Test
    fun givenBit64Final_whenAsBits_thenConvertsAsExpected() {
        val pow67 = BigInteger("2").pow(67).minus(BigInteger.ONE)
        val expectedBits = pow67.times(BigInteger("8"))

        // BigInteger.toByteArray returns 9 bytes (most significant
        // number stripped of leading zeros)
        val bPow67 = pow67.toByteArray()
        val bExpectedBits = expectedBits.toByteArray()

        val bits = Counter.Bit64.Final(
            lo = bPow67.beLongAt(1),
            hi = bPow67[0].toLong(),
        ).asBits()

        val expectedLo = bExpectedBits.beLongAt(1)
        val expectedHi = bExpectedBits[0].toLong()

        assertEquals(-8, expectedLo)
        assertEquals(63, expectedHi)

        assertEquals(expectedLo, bits.lo)
        assertEquals(expectedHi, bits.hi)
    }

    @Test
    fun givenBit64Final_whenAsBitsFromRandomLargeNumber_thenConvertsAsExpected() {
        repeat(500) {
            val number = BigInteger("2").pow(88).minus(
                BigInteger(
                    Random.Default.nextLong(Long.MAX_VALUE - 500_000_000, Long.MAX_VALUE).toString()
                )
            )
            val nBits = number.times(BigInteger("8"))

            val bNumber = number.toByteArray().to16Bytes()
            val bNBits = nBits.toByteArray().to16Bytes()

            val bits = Counter.Bit64.Final(
                lo = bNumber.beLongAt(8),
                hi = bNumber.beLongAt(0),
            ).asBits()

            val expectedLo = bNBits.beLongAt(8)
            val expectedHi = bNBits.beLongAt(0)

            assertEquals(expectedLo, bits.lo)
            assertEquals(expectedHi, bits.hi)
        }
    }

    private fun ByteArray.to16Bytes(): ByteArray {
        require(this.size <= 16) { "array.size is greater than 16 bytes..." }
        val b = ByteArray(16)
        copyInto(b, destinationOffset = 16 - size)
        return b
    }
}
