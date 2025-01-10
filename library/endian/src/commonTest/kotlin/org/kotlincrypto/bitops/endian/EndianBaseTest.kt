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
package org.kotlincrypto.bitops.endian

import kotlin.random.Random
import kotlin.test.*

abstract class EndianBaseTest(private val endian: Endian) {

    // None of the expected values contain -100 which is utilized
    // as an array fill value to check pack correctness
    private companion object {
        private val SHORTS = listOf(
               23754.toShort() to byteArrayOf(-54, 92),
            (-13451).toShort() to byteArrayOf(117, -53),
               14666.toShort() to byteArrayOf(74, 57),
               89641.toShort() to byteArrayOf(41, 94),
               19884.toShort() to byteArrayOf(-84, 77),
            (-10995).toShort() to byteArrayOf(13, -43),
        ).map { (number, expectedLE) ->
            Triple(number, expectedLE, expectedLE.reversedArray())
        }

        private val INTS = listOf(
             1767359 to byteArrayOf(-65, -9, 26, 0),
            -1059069 to byteArrayOf(3, -41, -17, -1),
             1419696 to byteArrayOf(-80, -87, 21, 0),
             1009491 to byteArrayOf(83, 103, 15, 0),
            -1988487 to byteArrayOf(121, -88, -31, -1),
             1099465 to byteArrayOf(-55, -58, 16, 0),
        ).map { (number, expectedLE) ->
            Triple(number, expectedLE, expectedLE.reversedArray())
        }

        private val LONGS = listOf(
             814651899345953L to byteArrayOf(33, 124, 19, -19, -21, -28, 2, 0),
             935788659919911L to byteArrayOf(39, -52, -88, 70, 24, 83, 3, 0),
            -189347651943587L to byteArrayOf(93, -125, 119, 16, -54, 83, -1, -1),
             876184514511999L to byteArrayOf(127, -60, -66, -102, -30, 28, 3, 0),
            -737571571757717L to byteArrayOf(107, 13, 120, -68, 46, 97, -3, -1),
             901834769984888L to byteArrayOf(120, 109, 28, -59, 54, 52, 3, 0),
        ).map { (number, expectedLE) ->
            Triple(number, expectedLE, expectedLE.reversedArray())
        }
    }

    private fun Triple<*, ByteArray, ByteArray>.expectedBytes(): ByteArray = when (endian) {
        is Endian.Big -> third
        is Endian.Little -> second
    }

    @Test
    fun givenEndian_whenShortOf_thenIsAsExpected() {
        SHORTS.forEach { value ->
            val b = value.expectedBytes()
            val actual = endian.shortOf(b[0], b[1])
            assertEquals(value.first, actual)
        }

        // Random values to/from
        repeat(50) {
            val expected = Random.Default.nextInt().toShort()
            val b = endian.pack(expected, ByteArray(Short.SIZE_BYTES), 0)
            val actual = endian.shortOf(b[0], b[1])
            assertEquals(expected, actual)
        }
    }

    @Test
    fun givenEndian_whenIntOf_thenIsAsExpected() {
        INTS.forEach { value ->
            val b = value.expectedBytes()
            val actual = endian.intOf(b[0], b[1], b[2], b[3])
            assertEquals(value.first, actual)
        }

        // Random values to/from
        repeat(50) {
            val expected = Random.Default.nextInt()
            val b = endian.pack(expected, ByteArray(Int.SIZE_BYTES), 0)
            val actual = endian.intOf(b[0], b[1], b[2], b[3])
            assertEquals(expected, actual)
        }
    }

    @Test
    fun givenEndian_whenLongOf_thenIsAsExpected() {
        LONGS.forEach { value ->
            val b = value.expectedBytes()
            val actual = endian.longOf(b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7])
            assertEquals(value.first, actual)
        }

        // Random values to/from
        repeat(50) {
            val expected = Random.Default.nextLong()
            val b = endian.pack(expected, ByteArray(Long.SIZE_BYTES), 0)
            val actual = endian.longOf(b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7])
            assertEquals(expected, actual)
        }
    }

    @Test
    fun givenEndian_whenShortFrom_thenIsAsExpected() {
        SHORTS.forEach { value ->
            val actual = endian.shortFrom(value.expectedBytes(), offset = 0)
            assertEquals(value.first, actual)
        }

        // Random values to/from
        repeat(50) {
            val expected = Random.Default.nextInt().toShort()
            val b = endian.pack(expected, ByteArray(Short.SIZE_BYTES), 0)
            val actual = endian.shortFrom(b, 0)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun givenEndian_whenIntFrom_thenIsAsExpected() {
        INTS.forEach { value ->
            val actual = endian.intFrom(value.expectedBytes(), offset = 0)
            assertEquals(value.first, actual)
        }

        // Random values to/from
        repeat(50) {
            val expected = Random.Default.nextInt()
            val b = endian.pack(expected, ByteArray(Int.SIZE_BYTES), 0)
            val actual = endian.intFrom(b, 0)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun givenEndian_whenLongFrom_thenIsAsExpected() {
        LONGS.forEach { value ->
            val actual = endian.longFrom(value.expectedBytes(), 0)
            assertEquals(value.first, actual)
        }

        // Random values to/from
        repeat(50) {
            val expected = Random.Default.nextLong()
            val b = endian.pack(expected, ByteArray(Long.SIZE_BYTES), 0)
            val actual = endian.longFrom(b, 0)
            assertEquals(expected, actual)
        }
    }

    @Test
    fun givenEndian_whenPackShort_thenIsAsExpected() {
        val b = ByteArray(Short.SIZE_BYTES)
        SHORTS.forEach { value ->
            endian.pack(value.first, b, 0)
            assertContentEquals(value.expectedBytes(), b)
        }

        val value = SHORTS.first()
        val number = value.first
        val expected = value.expectedBytes()
        val expectedNot: Byte = -100
        assertNotEquals(expectedNot, expected[0])
        assertNotEquals(expectedNot, expected[1])
        b.fill(expectedNot)

        endian.pack(number, b, 1, sourceIndexStart = 1)
        assertEquals(expectedNot, b[0])
        assertEquals(expected[1], b[1])
        b.fill(expectedNot)

        endian.pack(number, b, 0, sourceIndexStart = 1)
        assertEquals(expected[1], b[0])
        assertEquals(expectedNot, b[1])
        b.fill(expectedNot)

        endian.pack(number, b, 1, sourceIndexStart = 0, sourceIndexEnd = 1)
        assertEquals(expectedNot, b[0])
        assertEquals(expected[0], b[1])

        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, ByteArray(b.size - 1), 0)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = -1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = b.size + 1)
        }
        assertFailsWith<IllegalArgumentException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = b.size + 1)
        }
        assertFailsWith<IllegalArgumentException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = -1)
        }
    }

    @Test
    fun givenEndian_whenPackInt_thenIsAsExpected() {
        val b = ByteArray(Int.SIZE_BYTES)
        INTS.forEach { value ->
            endian.pack(value.first, b, 0)
            assertContentEquals(value.expectedBytes(), b)
        }

        val value = INTS.first()
        val number = value.first
        val expected = value.expectedBytes()
        val expectedNot: Byte = -100
        assertNotEquals(expectedNot, expected[0])
        assertNotEquals(expectedNot, expected[1])
        assertNotEquals(expectedNot, expected[2])
        assertNotEquals(expectedNot, expected[3])
        b.fill(expectedNot)

        endian.pack(number, b, 1, sourceIndexStart = 1)
        assertEquals(expectedNot, b[0])
        assertEquals(expected[1], b[1])
        assertEquals(expected[2], b[2])
        assertEquals(expected[3], b[3])
        b.fill(expectedNot)

        endian.pack(number, b, 0, sourceIndexStart = 1)
        assertEquals(expected[1], b[0])
        assertEquals(expected[2], b[1])
        assertEquals(expected[3], b[2])
        assertEquals(expectedNot, b[3])
        b.fill(expectedNot)

        endian.pack(number, b, 1, sourceIndexStart = 0, sourceIndexEnd = 2)
        assertEquals(expectedNot, b[0])
        assertEquals(expected[0], b[1])
        assertEquals(expected[1], b[2])
        assertEquals(expectedNot, b[3])

        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, ByteArray(b.size - 1), 0)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = -1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = b.size + 1)
        }
        assertFailsWith<IllegalArgumentException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = b.size + 1)
        }
        assertFailsWith<IllegalArgumentException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = -1)
        }
    }

    @Test
    fun givenEndian_whenPackLong_thenIsAsExpected() {
        val b = ByteArray(Long.SIZE_BYTES)
        LONGS.forEach { value ->
            endian.pack(value.first, b, 0)
            assertContentEquals(value.expectedBytes(), b)
        }

        val value = LONGS.first()
        val number = value.first
        val expected = value.expectedBytes()
        val expectedNot: Byte = -100
        assertNotEquals(expectedNot, expected[0])
        assertNotEquals(expectedNot, expected[1])
        assertNotEquals(expectedNot, expected[2])
        assertNotEquals(expectedNot, expected[3])
        assertNotEquals(expectedNot, expected[4])
        assertNotEquals(expectedNot, expected[5])
        assertNotEquals(expectedNot, expected[6])
        assertNotEquals(expectedNot, expected[7])
        b.fill(expectedNot)

        endian.pack(number, b, 1, sourceIndexStart = 1)
        assertEquals(expectedNot, b[0])
        assertEquals(expected[1], b[1])
        assertEquals(expected[2], b[2])
        assertEquals(expected[3], b[3])
        assertEquals(expected[4], b[4])
        assertEquals(expected[5], b[5])
        assertEquals(expected[6], b[6])
        assertEquals(expected[7], b[7])
        b.fill(expectedNot)

        endian.pack(number, b, 0, sourceIndexStart = 1)
        assertEquals(expected[1], b[0])
        assertEquals(expected[2], b[1])
        assertEquals(expected[3], b[2])
        assertEquals(expected[4], b[3])
        assertEquals(expected[5], b[4])
        assertEquals(expected[6], b[5])
        assertEquals(expected[7], b[6])
        assertEquals(expectedNot, b[7])
        b.fill(expectedNot)

        endian.pack(number, b, 1, sourceIndexStart = 0, sourceIndexEnd = 2)
        assertEquals(expectedNot, b[0])
        assertEquals(expected[0], b[1])
        assertEquals(expected[1], b[2])
        assertEquals(expectedNot, b[3])
        assertEquals(expectedNot, b[4])
        assertEquals(expectedNot, b[5])
        assertEquals(expectedNot, b[6])
        assertEquals(expectedNot, b[7])

        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, ByteArray(b.size - 1), 0)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = -1)
        }
        assertFailsWith<IndexOutOfBoundsException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = b.size + 1)
        }
        assertFailsWith<IllegalArgumentException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = b.size + 1)
        }
        assertFailsWith<IllegalArgumentException> {
            endian.pack(number, b, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = -1)
        }
    }

    @Test
    fun givenEndian_whenPackShortArray_thenIsAsExpected() {
        val expectedN = ShortArray(SHORTS.size)
        val expectedB = ByteArray(SHORTS.first().second.size * SHORTS.size)
        SHORTS.forEachIndexed { index, value ->
            val bytes = value.expectedBytes()
            expectedN[index] = value.first
            bytes.copyInto(expectedB, bytes.size * index)
        }

        assertContentEquals(expectedB, endian.pack(source = expectedN, dest = ByteArray(expectedB.size), destOffset = 0))
        assertContentEquals(expectedN, endian.pack(source = expectedB, dest = ShortArray(expectedN.size), destOffset = 0, 0, expectedB.size))

        // TODO assertFailsWith
    }

    @Test
    fun givenEndian_whenPackIntArray_thenIsAsExpected() {
        val expectedN = IntArray(INTS.size)
        val expectedB = ByteArray(INTS.first().second.size * INTS.size)
        INTS.forEachIndexed { index, value ->
            val bytes = value.expectedBytes()
            expectedN[index] = value.first
            bytes.copyInto(expectedB, bytes.size * index)
        }

        assertContentEquals(expectedB, endian.pack(source = expectedN, dest = ByteArray(expectedB.size), destOffset = 0))
        assertContentEquals(expectedN, endian.pack(source = expectedB, dest = IntArray(expectedN.size), destOffset = 0, 0, expectedB.size))

        // TODO assertFailsWith
    }

    @Test
    fun givenEndian_whenPackLongArray_thenIsAsExpected() {
        val expectedN = LongArray(LONGS.size)
        val expectedB = ByteArray(LONGS.first().second.size * LONGS.size)
        LONGS.forEachIndexed { index, value ->
            val bytes = value.expectedBytes()
            expectedN[index] = value.first
            bytes.copyInto(expectedB, bytes.size * index)
        }

        assertContentEquals(expectedB, endian.pack(source = expectedN, dest = ByteArray(expectedB.size), destOffset = 0))
        assertContentEquals(expectedN, endian.pack(source = expectedB, dest = LongArray(expectedN.size), destOffset = 0, 0, expectedB.size))

        // TODO assertFailsWith
    }
}
