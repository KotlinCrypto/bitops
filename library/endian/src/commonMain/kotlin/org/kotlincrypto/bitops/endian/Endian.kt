/*
 * Copyright (c) 2025 KotlinCrypto
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
@file:Suppress("KotlinRedundantDiagnosticSuppress", "NOTHING_TO_INLINE", "RemoveRedundantQualifierName")

package org.kotlincrypto.bitops.endian

import org.kotlincrypto.bitops.endian.internal.*
import kotlin.jvm.JvmStatic

/**
 * [Big] & [Little] endian utilities.
 * */
public sealed class Endian private constructor() {

    /**
     * Convert `2` bytes to a Short.
     *
     * e.g.
     *
     *     val source = 2702.toShort()
     *     val dest = ByteArray(2) { -100 }
     *     println(dest.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [10, -114]
     *     Endian.Big.shortOf(
     *         dest[0],
     *         dest[1],
     *     ).let { println(it) }
     *     // 2702
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-114, 10]
     *     Endian.Little.shortOf(
     *         dest[0],
     *         dest[1],
     *     ).let { println(it) }
     *     // 2702
     *
     * @see [shortFrom]
     * */
    public abstract fun shortOf(
        b0: Byte,
        b1: Byte,
    ): Short

    /**
     * Convert `4` bytes to an Int.
     *
     * e.g.
     *
     *     val source = -77362181
     *     val dest = ByteArray(4) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, 99, -117, -5]
     *     Endian.Big.intOf(
     *         dest[0],
     *         dest[1],
     *         dest[2],
     *         dest[3],
     *     ).let { println(it) }
     *     // -77362181
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, -117, 99, -5]
     *     Endian.Little.intOf(
     *         dest[0],
     *         dest[1],
     *         dest[2],
     *         dest[3],
     *     ).let { println(it) }
     *     // -77362181
     *
     * @see [intFrom]
     * */
    public abstract fun intOf(
        b0: Byte,
        b1: Byte,
        b2: Byte,
        b3: Byte,
    ): Int

    /**
     * Convert `8` bytes to a Long.
     *
     * e.g.
     *
     *     val source = 9223372034707292160L
     *     val dest = ByteArray(8) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     Endian.Big.longOf(
     *         dest[0],
     *         dest[1],
     *         dest[2],
     *         dest[3],
     *         dest[4],
     *         dest[5],
     *         dest[6],
     *         dest[7],
     *     ).let { println(it) }
     *     // 9223372034707292160
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     Endian.Little.longOf(
     *         dest[0],
     *         dest[1],
     *         dest[2],
     *         dest[3],
     *         dest[4],
     *         dest[5],
     *         dest[6],
     *         dest[7],
     *     ).let { println(it) }
     *     // 9223372034707292160
     *
     * @see [longFrom]
     * */
    public abstract fun longOf(
        b0: Byte,
        b1: Byte,
        b2: Byte,
        b3: Byte,
        b4: Byte,
        b5: Byte,
        b6: Byte,
        b7: Byte,
    ): Long

    /**
     * Convert `2` bytes from [source] ByteArray, starting at index [offset], to a Short.
     *
     * e.g.
     *
     *     val source = 2702.toShort()
     *     val dest = ByteArray(2) { -100 }
     *     println(dest.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [10, -114]
     *     println(Endian.Big.shortFrom(dest, 0))
     *     // 2702
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-114, 10]
     *     println(Endian.Little.shortFrom(dest, 0))
     *     // 2702
     *
     * @see [shortOf]
     * @see [Big.beShortAt]
     * @see [Little.leShortAt]
     *
     * @param [source] The ByteArray to access bytes from.
     * @param [offset] The index to start at when retrieving bytes from [source].
     *
     * @throws [IndexOutOfBoundsException] when not enough bytes are available in array from [offset].
     * */
    public abstract fun shortFrom(
        source: ByteArray,
        offset: Int,
    ): Short

    /**
     * Convert `4` bytes from [source] ByteArray, starting at index [offset], to an Int.
     *
     * e.g.
     *
     *     val source = -77362181
     *     val dest = ByteArray(4) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, 99, -117, -5]
     *     println(Endian.Big.intFrom(dest, 0))
     *     // -77362181
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, -117, 99, -5]
     *     println(Endian.Little.intFrom(dest, 0))
     *     // -77362181
     *
     * @see [intOf]
     * @see [Big.beIntAt]
     * @see [Little.leIntAt]
     *
     * @param [source] The ByteArray to access bytes from.
     * @param [offset] The index to start at when retrieving bytes from [source].
     *
     * @throws [IndexOutOfBoundsException] when not enough bytes are available in array from [offset].
     * */
    public abstract fun intFrom(
        source: ByteArray,
        offset: Int,
    ): Int

    /**
     * Convert `8` bytes from [source] ByteArray, starting at index [offset], to a Long.
     *
     * e.g.
     *
     *     val source = 9223372034707292160L
     *     val dest = ByteArray(8) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     println(Endian.Big.longFrom(dest, 0))
     *     // 9223372034707292160
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     println(Endian.Little.longFrom(dest, 0))
     *     // 9223372034707292160
     *
     * @see [longOf]
     * @see [Big.beLongAt]
     * @see [Little.leLongAt]
     *
     * @param [source] The ByteArray to access bytes from.
     * @param [offset] The index to start at when retrieving bytes from [source].
     *
     * @throws [IndexOutOfBoundsException] when not enough bytes are available in array from [offset].
     * */
    public abstract fun longFrom(
        source: ByteArray,
        offset: Int,
    ): Long

    /**
     * Packs `2` bytes from [source] Short into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = 2702.toShort()
     *     val dest = ByteArray(2) { -100 }
     *     println(dest.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [10, -114]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-114, 10]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The Short to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: Short,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = Short.SIZE_BYTES,
            sourceByteIndexStart = 0,
            sourceByteIndexEnd = Short.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset)
    }

    /**
     * Packs bytes from [source] Short (max `2` bytes) from [sourceIndexStart] (inclusive) to
     * [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = 2702.toShort()
     *     val dest = ByteArray(2) { -100 }
     *     println(dest.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [10, -114]
     *     dest.fill(-100)
     *
     *     Endian.Big.pack(source, dest, 1, sourceIndexStart = 0, sourceIndexEnd = 1)
     *     println(dest.toList())
     *     // [-100, 10]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-114, 10]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 1, sourceIndexStart = 1)
     *     println(dest.toList())
     *     // [-100, 10]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The Short to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] byte subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] byte subrange, [Short.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the number's byte indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: Short,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int = Short.SIZE_BYTES,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = Short.SIZE_BYTES,
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs bytes from [source] ShortArray (max `size * 2` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = ShortArray(3) { i -> (i + 2701).toShort() }
     *     val dest = ByteArray(source.size * Short.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100]
     *     // [2701, 2702, 2703]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.pack(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [10, -115, 10, -114, 10, -113]
     *     // [2701, 2702, 2703]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.pack(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-115, 10, -114, 10, -113, 10]
     *     // [0, 2702, 2703]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The ShortArray to retrieve Shorts from to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange, `0` by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange, `source.size` by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the array indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: ShortArray,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int = 0,
        sourceIndexEnd: Int = source.size,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = source.size * Short.SIZE_BYTES,
            sourceByteIndexStart = sourceIndexStart * Short.SIZE_BYTES,
            sourceByteIndexEnd = sourceIndexEnd * Short.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs Shorts from [source] ByteArray (max `size / 2` Shorts) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ShortArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = ShortArray(3) { i -> (i + 2701).toShort() }
     *     val dest = ByteArray(source.size * Short.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100]
     *     // [2701, 2702, 2703]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.pack(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [10, -115, 10, -114, 10, -113]
     *     // [2701, 2702, 2703]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.pack(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-115, 10, -114, 10, -113, 10]
     *     // [0, 2702, 2703]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The ByteArray to retrieve bytes from to convert into Shorts.
     * @param [dest] The ShortArray to pack Shorts into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the array indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] if [sourceIndexStart] to [sourceIndexEnd] is not a factor of [Short.SIZE_BYTES]
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: ByteArray,
        dest: ShortArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int,
    ): ShortArray {
        checkPackParameters(
            destByteCapacity = dest.size * Short.SIZE_BYTES,
            destByteIndexStart = destOffset * Short.SIZE_BYTES,
            destByteIndexEnd = dest.size * Short.SIZE_BYTES,
            sourceByteAvailability = source.size,
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
        )
        checkSourceRemainder(
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs `4` bytes from [source] Int into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = -77362181
     *     val dest = ByteArray(4) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, 99, -117, -5]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, -117, 99, -5]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The Int to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: Int,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = Int.SIZE_BYTES,
            sourceByteIndexStart = 0,
            sourceByteIndexEnd = Int.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset)
    }

    /**
     * Packs bytes from [source] Int (max `4` bytes) from [sourceIndexStart] (inclusive) to
     * [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = -77362181
     *     val dest = ByteArray(4) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, 99, -117, -5]
     *     dest.fill(-100)
     *
     *     Endian.Big.pack(source, dest, 2, sourceIndexStart = 1, sourceIndexEnd = 3)
     *     println(dest.toList())
     *     // [-100, -100, 99, -117]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, -117, 99, -5]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 1, sourceIndexStart = 3)
     *     println(dest.toList())
     *     // [-100, -5, -100, -100]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The Int to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] byte subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] byte subrange, [Int.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the number's byte indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: Int,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int = Int.SIZE_BYTES,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = Int.SIZE_BYTES,
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs bytes from [source] IntArray (max `size * 4` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = IntArray(2) { i -> i - 77362181 }
     *     val dest = ByteArray(source.size * Int.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *     // [-77362181, -77362180]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.pack(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, 99, -117, -5, -5, 99, -117, -4]
     *     // [-77362181, -77362180]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.pack(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, -117, 99, -5, -4, -117, 99, -5]
     *     // [0, -77362180]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The IntArray to retrieve Ints from to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange, `0` by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange, `source.size` by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the array indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: IntArray,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int = 0,
        sourceIndexEnd: Int = source.size,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = source.size * Int.SIZE_BYTES,
            sourceByteIndexStart = sourceIndexStart * Int.SIZE_BYTES,
            sourceByteIndexEnd = sourceIndexEnd * Int.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs Ints from [source] ByteArray (max `size / 4` Ints) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] IntArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = IntArray(2) { i -> i - 77362181 }
     *     val dest = ByteArray(source.size * Int.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *     // [-77362181, -77362180]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.pack(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, 99, -117, -5, -5, 99, -117, -4]
     *     // [-77362181, -77362180]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.pack(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, -117, 99, -5, -4, -117, 99, -5]
     *     // [0, -77362180]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The ByteArray to retrieve bytes from to convert into Ints.
     * @param [dest] The IntArray to pack Ints into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the array indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] if [sourceIndexStart] to [sourceIndexEnd] is not a factor of [Int.SIZE_BYTES]
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: ByteArray,
        dest: IntArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int,
    ): IntArray {
        checkPackParameters(
            destByteCapacity = dest.size * Int.SIZE_BYTES,
            destByteIndexStart = destOffset * Int.SIZE_BYTES,
            destByteIndexEnd = dest.size * Int.SIZE_BYTES,
            sourceByteAvailability = source.size,
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
        )
        checkSourceRemainder(
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs `8` bytes from [source] Long into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = 9223372034707292160L
     *     val dest = ByteArray(8) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The Long to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: Long,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = Long.SIZE_BYTES,
            sourceByteIndexStart = 0,
            sourceByteIndexEnd = Long.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset)
    }

    /**
     * Packs bytes from [source] Long (max `8` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = 9223372034707292160L
     *     val dest = ByteArray(8) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     dest.fill(-100)
     *
     *     Endian.Big.pack(source, dest, 2, sourceIndexStart = 1, sourceIndexEnd = 7)
     *     println(dest.toList())
     *     // [-100, -100, -1, -1, -1, -128, 0, 0]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     println(dest.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 1, sourceIndexStart = 3)
     *     println(dest.toList())
     *     // [-100, -128, -1, -1, -1, 127, -100, -100]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The Long to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] byte subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] byte subrange, [Long.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the number's byte indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: Long,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int = Long.SIZE_BYTES,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = Long.SIZE_BYTES,
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs bytes from [source] LongArray (max `size * 8` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = LongArray(2) { i -> i + 9223372034707292160L }
     *     val dest = ByteArray(source.size * Long.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100]
     *     // [9223372034707292160, 9223372034707292161]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Big.pack(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0, 127, -1, -1, -1, -128, 0, 0, 1]
     *     // [9223372034707292160, 9223372034707292161]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Little.pack(source = dest, dest = source, 1, dest.size - 8, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127, 1, 0, 0, -128, -1, -1, -1, 127]
     *     // [0, 9223372034707292161]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The LongArray to retrieve Longs from to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange, `0` by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange, `source.size` by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the array indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] when byte subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: LongArray,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int = 0,
        sourceIndexEnd: Int = source.size,
    ): ByteArray {
        checkPackParameters(
            destByteCapacity = dest.size,
            destByteIndexStart = destOffset,
            destByteIndexEnd = dest.size,
            sourceByteAvailability = source.size * Long.SIZE_BYTES,
            sourceByteIndexStart = sourceIndexStart * Long.SIZE_BYTES,
            sourceByteIndexEnd = sourceIndexEnd * Long.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs Longs from [source] ByteArray (max `size / 8` Longs) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] LongArray, starting at [destOffset].
     *
     * e.g.
     *
     *     val source = LongArray(2) { i -> i + 9223372034707292160L }
     *     val dest = ByteArray(source.size * Long.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100]
     *     // [9223372034707292160, 9223372034707292161]
     *
     *     Endian.Big.pack(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Big.pack(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0, 127, -1, -1, -1, -128, 0, 0, 1]
     *     // [9223372034707292160, 9223372034707292161]
     *     dest.fill(-100)
     *
     *     Endian.Little.pack(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Little.pack(source = dest, dest = source, 1, dest.size - 8, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127, 1, 0, 0, -128, -1, -1, -1, 127]
     *     // [0, 9223372034707292161]
     *
     * @see [packUnsafe]
     * @see [Big.bePackInto]
     * @see [Little.lePackInto]
     *
     * @param [source] The ByteArray to retrieve bytes from to convert into Longs.
     * @param [dest] The LongArray to pack Longs into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when [sourceIndexStart] or
     *   [sourceIndexEnd] are out of range of the array indices, or when `sourceIndexStart > sourceIndexEnd`.
     * @throws [IndexOutOfBoundsException] if [sourceIndexStart] to [sourceIndexEnd] is not a factor of [Long.SIZE_BYTES]
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the [dest] array starting at
     *   the specified [destOffset], or when that index is out of the [dest] array indices range.
     * */
    public fun pack(
        source: ByteArray,
        dest: LongArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int,
    ): LongArray {
        checkPackParameters(
            destByteCapacity = dest.size * Long.SIZE_BYTES,
            destByteIndexStart = destOffset * Long.SIZE_BYTES,
            destByteIndexEnd = dest.size * Long.SIZE_BYTES,
            sourceByteAvailability = source.size,
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
        )
        checkSourceRemainder(
            sourceByteIndexStart = sourceIndexStart,
            sourceByteIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
        )
        return packUnsafe(source, dest, destOffset, sourceIndexStart, sourceIndexEnd)
    }

    /**
     * Packs `2` bytes from [source] Short into [dest] ByteArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = 2702.toShort()
     *     val dest = ByteArray(2) { -100 }
     *     println(dest.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [10, -114]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [-114, 10]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The Short to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        source: Short,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray

    /**
     * Packs bytes from [source] Short (max `2` bytes) from [sourceIndexStart] (inclusive) to
     * [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset]. If [sourceIndexStart]
     * is `0` and [sourceIndexEnd] is [Short.SIZE_BYTES], the more performant [packUnsafe] function
     * will be utilized.
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = 2702.toShort()
     *     val dest = ByteArray(2) { -100 }
     *     println(dest.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [10, -114]
     *     dest.fill(-100)
     *
     *     Endian.Big.packUnsafe(source, dest, 1, sourceIndexStart = 0, sourceIndexEnd = 1)
     *     println(dest.toList())
     *     // [-100, 10]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [-114, 10]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 1, sourceIndexStart = 1)
     *     println(dest.toList())
     *     // [-100, 10]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The Short to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] byte subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] byte subrange, [Short.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        source: Short,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int = Short.SIZE_BYTES,
    ): ByteArray

    /**
     * Packs bytes from [source] ShortArray (max `size * 2` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = ShortArray(3) { i -> (i + 2701).toShort() }
     *     val dest = ByteArray(source.size * Short.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100]
     *     // [2701, 2702, 2703]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.packUnsafe(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [10, -115, 10, -114, 10, -113]
     *     // [2701, 2702, 2703]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.packUnsafe(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-115, 10, -114, 10, -113, 10]
     *     // [0, 2702, 2703]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The ShortArray to retrieve Shorts from to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange, `0` by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange, `source.size` by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] or [source] is accessed.
     * */
    public abstract fun packUnsafe(
        source: ShortArray,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int = 0,
        sourceIndexEnd: Int = source.size,
    ): ByteArray

    /**
     * Packs Shorts from [source] ByteArray (max `size / 2` Shorts) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ShortArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = ShortArray(3) { i -> (i + 2701).toShort() }
     *     val dest = ByteArray(source.size * Short.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100]
     *     // [2701, 2702, 2703]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.packUnsafe(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [10, -115, 10, -114, 10, -113]
     *     // [2701, 2702, 2703]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.packUnsafe(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-115, 10, -114, 10, -113, 10]
     *     // [0, 2702, 2703]
     *
     * @see [longFrom]
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The ByteArray to retrieve bytes from to convert into Shorts.
     * @param [dest] The ShortArray to pack Shorts into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] or [source] is accessed.
     * */
    public abstract fun packUnsafe(
        source: ByteArray,
        dest: ShortArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int,
    ): ShortArray

    /**
     * Packs `4` bytes from [source] Int into [dest] ByteArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = -77362181
     *     val dest = ByteArray(4) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, 99, -117, -5]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, -117, 99, -5]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The Int to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        source: Int,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray

    /**
     * Packs bytes from [source] Int (max `4` bytes) from [sourceIndexStart] (inclusive) to
     * [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset]. If [sourceIndexStart]
     * is `0` and [sourceIndexEnd] is [Int.SIZE_BYTES], the more performant [packUnsafe] function
     * will be utilized.
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = -77362181
     *     val dest = ByteArray(4) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, 99, -117, -5]
     *     dest.fill(-100)
     *
     *     Endian.Big.packUnsafe(source, dest, 2, sourceIndexStart = 1, sourceIndexEnd = 3)
     *     println(dest.toList())
     *     // [-100, -100, 99, -117]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [-5, -117, 99, -5]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 1, sourceIndexStart = 3)
     *     println(dest.toList())
     *     // [-100, -5, -100, -100]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The Int to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] byte subrange, 0 by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] byte subrange, [Int.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        source: Int,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int = Int.SIZE_BYTES,
    ): ByteArray

    /**
     * Packs bytes from [source] IntArray (max `size * 4` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = IntArray(2) { i -> i - 77362181 }
     *     val dest = ByteArray(source.size * Int.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *     // [-77362181, -77362180]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.packUnsafe(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, 99, -117, -5, -5, 99, -117, -4]
     *     // [-77362181, -77362180]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.packUnsafe(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, -117, 99, -5, -4, -117, 99, -5]
     *     // [0, -77362180]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The IntArray to retrieve Ints from to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange, `0` by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange, `source.size` by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] or [source] is accessed.
     * */
    public abstract fun packUnsafe(
        source: IntArray,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int = 0,
        sourceIndexEnd: Int = source.size,
    ): ByteArray

    /**
     * Packs Ints from [source] ByteArray (max `size / 4` Ints) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] IntArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = IntArray(2) { i -> i - 77362181 }
     *     val dest = ByteArray(source.size * Int.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *     // [-77362181, -77362180]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Big.packUnsafe(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, 99, -117, -5, -5, 99, -117, -4]
     *     // [-77362181, -77362180]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     source.fill(0)
     *
     *     Endian.Little.packUnsafe(source = dest, dest = source, 1, dest.size - 4, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-5, -117, 99, -5, -4, -117, 99, -5]
     *     // [0, -77362180]
     *
     * @see [longFrom]
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The ByteArray to retrieve bytes from to convert into Ints.
     * @param [dest] The IntArray to pack Ints into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] or [source] is accessed.
     * */
    public abstract fun packUnsafe(
        source: ByteArray,
        dest: IntArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int,
    ): IntArray

    /**
     * Packs `8` bytes from [source] Long into [dest] ByteArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = 9223372034707292160L
     *     val dest = ByteArray(8) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The Long to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        source: Long,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray

    /**
     * Packs bytes from [source] Long (max `8` bytes) from [sourceIndexStart] (inclusive) to
     * [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset]. If [sourceIndexStart]
     * is `0` and [sourceIndexEnd] is [Long.SIZE_BYTES], the more performant [packUnsafe] function
     * will be utilized.
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = 9223372034707292160L
     *     val dest = ByteArray(8) { -100 }
     *     println(dest.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     dest.fill(-100)
     *
     *     Endian.Big.packUnsafe(source, dest, 2, sourceIndexStart = 1, sourceIndexEnd = 7)
     *     println(dest.toList())
     *     // [-100, -100, -1, -1, -1, -128, 0, 0]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     println(dest.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 1, sourceIndexStart = 3)
     *     println(dest.toList())
     *     // [-100, -128, -1, -1, -1, 127, -100, -100]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The Long to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] byte subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] byte subrange, [Long.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        source: Long,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int = Long.SIZE_BYTES,
    ): ByteArray

    /**
     * Packs bytes from [source] LongArray (max `size * 8` bytes) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] ByteArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = LongArray(2) { i -> i + 9223372034707292160L }
     *     val dest = ByteArray(source.size * Long.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100]
     *     // [9223372034707292160, 9223372034707292161]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Big.packUnsafe(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0, 127, -1, -1, -1, -128, 0, 0, 1]
     *     // [9223372034707292160, 9223372034707292161]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Little.packUnsafe(source = dest, dest = source, 1, dest.size - 8, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127, 1, 0, 0, -128, -1, -1, -1, 127]
     *     // [0, 9223372034707292161]
     *
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The LongArray to retrieve Longs from to convert into bytes.
     * @param [dest] The ByteArray to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange, `0` by default.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange, `source.size` by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] or [source] is accessed.
     * */
    public abstract fun packUnsafe(
        source: LongArray,
        dest: ByteArray,
        destOffset: Int,
        sourceIndexStart: Int = 0,
        sourceIndexEnd: Int = source.size,
    ): ByteArray

    /**
     * Packs Longs from [source] ByteArray (max `size / 8` Longs) from [sourceIndexStart] (inclusive)
     * to [sourceIndexEnd] (exclusive) into [dest] LongArray, starting at [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before altering
     * the [dest] array, but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val source = LongArray(2) { i -> i + 9223372034707292160L }
     *     val dest = ByteArray(source.size * Long.SIZE_BYTES) { -100 }
     *     println(dest.toList())
     *     println(source.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100]
     *     // [9223372034707292160, 9223372034707292161]
     *
     *     Endian.Big.packUnsafe(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Big.packUnsafe(source = dest, dest = source, 0, 0, sourceIndexEnd = dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0, 127, -1, -1, -1, -128, 0, 0, 1]
     *     // [9223372034707292160, 9223372034707292161]
     *     dest.fill(-100)
     *
     *     Endian.Little.packUnsafe(source, dest, 0)
     *     source.fill(0L)
     *
     *     Endian.Little.packUnsafe(source = dest, dest = source, 1, dest.size - 8, dest.size)
     *     println(dest.toList())
     *     println(source.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127, 1, 0, 0, -128, -1, -1, -1, 127]
     *     // [0, 9223372034707292161]
     *
     * @see [longFrom]
     * @see [Big.bePackIntoUnsafe]
     * @see [Little.lePackIntoUnsafe]
     *
     * @param [source] The ByteArray to retrieve bytes from to convert into Longs.
     * @param [dest] The LongArray to pack Longs into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [sourceIndexStart] The beginning (inclusive) of the [source] subrange.
     * @param [sourceIndexEnd] The end (exclusive) of the [source] subrange.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if an invalid index in [dest] or [source] is accessed.
     * */
    public abstract fun packUnsafe(
        source: ByteArray,
        dest: LongArray,
        destOffset: Int,
        sourceIndexStart: Int,
        sourceIndexEnd: Int,
    ): LongArray

    /**
     * Implementation of the [Endian] abstraction which performs all operations using
     * big-endian byte order.
     *
     * @see [Little]
     * */
    public data object Big: Endian() {

        /** Syntactic Sugar. See [Endian.Big.shortFrom] */
        @JvmStatic
        public inline fun ByteArray.beShortAt(
            offset: Int,
        ): Short = Big.shortFrom(this, offset)

        /** Syntactic Sugar. See [Endian.Big.intFrom] */
        @JvmStatic
        public inline fun ByteArray.beIntAt(
            offset: Int,
        ): Int = Big.intFrom(this, offset)

        /** Syntactic Sugar. See [Endian.Big.longFrom] */
        @JvmStatic
        public inline fun ByteArray.beLongAt(
            offset: Int,
        ): Long = Big.longFrom(this, offset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun Short.bePackInto(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Big.pack(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun Short.bePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Short.SIZE_BYTES,
        ): ByteArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ShortArray.bePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePackInto(
            dest: ShortArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): ShortArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun Int.bePackInto(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Big.pack(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun Int.bePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Int.SIZE_BYTES,
        ): ByteArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun IntArray.bePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePackInto(
            dest: IntArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): IntArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun Long.bePackInto(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Big.pack(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun Long.bePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Long.SIZE_BYTES,
        ): ByteArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun LongArray.bePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePackInto(
            dest: LongArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): LongArray = Big.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun Short.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun Short.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Short.SIZE_BYTES,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ShortArray.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackIntoUnsafe(
            dest: ShortArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): ShortArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun Int.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun Int.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Int.SIZE_BYTES,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun IntArray.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackIntoUnsafe(
            dest: IntArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): IntArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun Long.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun Long.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Long.SIZE_BYTES,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun LongArray.bePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackIntoUnsafe(
            dest: LongArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): LongArray = Big.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        public override fun shortOf(
            b0: Byte,
            b1: Byte,
        ): Short = B0(b0).toBEShort(b1)

        public override fun intOf(
            b0: Byte,
            b1: Byte,
            b2: Byte,
            b3: Byte,
        ): Int = B0(b0).toBEInt(b1, b2, b3)

        public override fun longOf(
            b0: Byte,
            b1: Byte,
            b2: Byte,
            b3: Byte,
            b4: Byte,
            b5: Byte,
            b6: Byte,
            b7: Byte,
        ): Long = B0(b0).toBELong(b1, b2, b3, b4, b5, b6, b7)

        public override fun shortFrom(
            source: ByteArray,
            offset: Int,
        ): Short = source.unpackBEShort(offset)

        public override fun intFrom(
            source: ByteArray,
            offset: Int,
        ): Int = source.unpackBEInt(offset)

        public override fun longFrom(
            source: ByteArray,
            offset: Int
        ): Long = source.unpackBELong(offset)

        public override fun packUnsafe(
            source: Short,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packBEShort(source, destOffset)

        public override fun packUnsafe(
            source: Short,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int, // = Short.SIZE_BYTES
        ): ByteArray = dest.packNumberAllElsePartial(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
            packNumber = { packBEShort(source, destOffset) },
            ushr = { bits -> (source.toInt() ushr (8 - bits)).toByte() },
        )

        public override fun packUnsafe(
            source: ShortArray,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int, // = 0
            sourceIndexEnd: Int, // = source.size
        ): ByteArray = dest.packArray(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
            packNumber = { sourcePos, destPos -> packBEShort(source[sourcePos], destPos) },
        )

        public override fun packUnsafe(
            source: ByteArray,
            dest: ShortArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): ShortArray = dest.packArray(
            source = source,
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
            unpackNumber = { sourcePos, destPos -> dest[destPos] = unpackBEShort(sourcePos) },
        )

        public override fun packUnsafe(
            source: Int,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packBEInt(source, destOffset)

        public override fun packUnsafe(
            source: Int,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int, // = Int.SIZE_BYTES
        ): ByteArray = dest.packNumberAllElsePartial(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
            packNumber = { packBEInt(source, destOffset) },
            ushr = { bits -> (source ushr (24 - bits)).toByte() },
        )

        public override fun packUnsafe(
            source: IntArray,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int, // = 0
            sourceIndexEnd: Int, // = source.size
        ): ByteArray = dest.packArray(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
            packNumber = { sourcePos, destPos -> packBEInt(source[sourcePos], destPos) },
        )

        public override fun packUnsafe(
            source: ByteArray,
            dest: IntArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): IntArray = dest.packArray(
            source = source,
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
            unpackNumber = { sourcePos, destPos -> dest[destPos] = unpackBEInt(sourcePos) },
        )

        public override fun packUnsafe(
            source: Long,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packBELong(source, destOffset)

        public override fun packUnsafe(
            source: Long,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int, // = Long.SIZE_BYTES
        ): ByteArray = dest.packNumberAllElsePartial(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
            packNumber = { packBELong(source, destOffset) },
            ushr = { bits -> (source ushr (56 - bits)).toByte() },
        )

        public override fun packUnsafe(
            source: LongArray,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int, // = 0
            sourceIndexEnd: Int, // = source.size
        ): ByteArray = dest.packArray(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
            packNumber = { sourcePos, destPos -> packBELong(source[sourcePos], destPos) },
        )

        public override fun packUnsafe(
            source: ByteArray,
            dest: LongArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): LongArray = dest.packArray(
            source = source,
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
            unpackNumber = { sourcePos, destPos -> dest[destPos] = source.unpackBELong(sourcePos) },
        )
    }

    /**
     * Implementation of the [Endian] abstraction which performs all operations using
     * little-endian byte order.
     *
     * @see [Big]
     * */
    public data object Little: Endian() {

        /** Syntactic Sugar. See [Endian.Little.shortFrom] */
        @JvmStatic
        public inline fun ByteArray.leShortAt(
            offset: Int,
        ): Short = Little.shortFrom(this, offset)

        /** Syntactic Sugar. See [Endian.Little.intFrom] */
        @JvmStatic
        public inline fun ByteArray.leIntAt(
            offset: Int,
        ): Int = Little.intFrom(this, offset)

        /** Syntactic Sugar. See [Endian.Little.longFrom] */
        @JvmStatic
        public inline fun ByteArray.leLongAt(
            offset: Int,
        ): Long = Little.longFrom(this, offset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun Short.lePackInto(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Little.pack(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun Short.lePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Short.SIZE_BYTES,
        ): ByteArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ShortArray.lePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePackInto(
            dest: ShortArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): ShortArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun Int.lePackInto(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Little.pack(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun Int.lePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Int.SIZE_BYTES,
        ): ByteArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun IntArray.lePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePackInto(
            dest: IntArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): IntArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun Long.lePackInto(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Little.pack(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun Long.lePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Long.SIZE_BYTES,
        ): ByteArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun LongArray.lePackInto(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePackInto(
            dest: LongArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): LongArray = Little.pack(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun Short.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun Short.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Short.SIZE_BYTES,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ShortArray.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackIntoUnsafe(
            dest: ShortArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): ShortArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun Int.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun Int.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Int.SIZE_BYTES,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun IntArray.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackIntoUnsafe(
            dest: IntArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): IntArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun Long.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun Long.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int = Long.SIZE_BYTES,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun LongArray.lePackIntoUnsafe(
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int = 0,
            sourceIndexEnd: Int = this.size,
        ): ByteArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackIntoUnsafe(
            dest: LongArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): LongArray = Little.packUnsafe(this, dest, destOffset, sourceIndexStart, sourceIndexEnd)

        public override fun shortOf(
            b0: Byte,
            b1: Byte,
        ): Short = B0(b0).toLEShort(b1)

        public override fun intOf(
            b0: Byte,
            b1: Byte,
            b2: Byte,
            b3: Byte,
        ): Int = B0(b0).toLEInt(b1, b2, b3)

        public override fun longOf(
            b0: Byte,
            b1: Byte,
            b2: Byte,
            b3: Byte,
            b4: Byte,
            b5: Byte,
            b6: Byte,
            b7: Byte,
        ): Long = B0(b0).toLELong(b1, b2, b3, b4, b5, b6, b7)

        public override fun shortFrom(
            source: ByteArray,
            offset: Int,
        ): Short = source.unpackLEShort(offset)

        public override fun intFrom(
            source: ByteArray,
            offset: Int,
        ): Int = source.unpackLEInt(offset)

        public override fun longFrom(
            source: ByteArray,
            offset: Int
        ): Long = source.unpackLELong(offset)

        public override fun packUnsafe(
            source: Short,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packLEShort(source, destOffset)

        public override fun packUnsafe(
            source: Short,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int, // = Short.SIZE_BYTES
        ): ByteArray = dest.packNumberAllElsePartial(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
            packNumber = { packLEShort(source, destOffset) },
            ushr = { bits -> (source.toInt() ushr bits).toByte() },
        )

        public override fun packUnsafe(
            source: ShortArray,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int, // = 0
            sourceIndexEnd: Int, // = source.size
        ): ByteArray = dest.packArray(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
            packNumber = { sourcePos, destPos -> packLEShort(source[sourcePos], destPos) },
        )

        public override fun packUnsafe(
            source: ByteArray,
            dest: ShortArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): ShortArray = dest.packArray(
            source = source,
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Short.SIZE_BYTES,
            unpackNumber = { sourcePos, destPos -> dest[destPos] = source.unpackLEShort(sourcePos) },
        )

        public override fun packUnsafe(
            source: Int,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packLEInt(source, destOffset)

        public override fun packUnsafe(
            source: Int,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int, // = Int.SIZE_BYTES
        ): ByteArray = dest.packNumberAllElsePartial(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
            packNumber = { packLEInt(source, destOffset) },
            ushr = { bits -> (source ushr bits).toByte() },
        )

        public override fun packUnsafe(
            source: IntArray,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int, // = 0
            sourceIndexEnd: Int, // = source.size
        ): ByteArray = dest.packArray(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
            packNumber = { sourcePos, destPos -> packLEInt(source[sourcePos], destPos) },
        )

        public override fun packUnsafe(
            source: ByteArray,
            dest: IntArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): IntArray = dest.packArray(
            source = source,
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Int.SIZE_BYTES,
            unpackNumber = { sourcePos, destPos -> dest[destPos] = source.unpackLEInt(sourcePos) },
        )

        public override fun packUnsafe(
            source: Long,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packLELong(source, destOffset)

        public override fun packUnsafe(
            source: Long,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int, // = Long.SIZE_BYTES
        ): ByteArray = dest.packNumberAllElsePartial(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
            packNumber = { packLELong(source, destOffset) },
            ushr = { bits -> (source ushr bits).toByte() },
        )

        public override fun packUnsafe(
            source: LongArray,
            dest: ByteArray,
            destOffset: Int,
            sourceIndexStart: Int, // = 0
            sourceIndexEnd: Int, // = source.size
        ): ByteArray = dest.packArray(
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
            packNumber = { sourcePos, destPos -> packLELong(source[sourcePos], destPos) },
        )

        public override fun packUnsafe(
            source: ByteArray,
            dest: LongArray,
            destOffset: Int,
            sourceIndexStart: Int,
            sourceIndexEnd: Int,
        ): LongArray = dest.packArray(
            source = source,
            destOffset = destOffset,
            sourceIndexStart = sourceIndexStart,
            sourceIndexEnd = sourceIndexEnd,
            numberSizeBytes = Long.SIZE_BYTES,
            unpackNumber = { sourcePos, destPos -> dest[destPos] = source.unpackLELong(sourcePos) },
        )
    }

    /** @suppress */
    public final override fun toString(): String = when (this) {
        is Big -> "Endian.Big"
        is Little -> "Endian.Little"
    }

    private companion object {

        @JvmStatic
        private fun checkPackParameters(
            destByteCapacity: Int,
            destByteIndexStart: Int,
            destByteIndexEnd: Int,
            sourceByteAvailability: Int,
            sourceByteIndexStart: Int,
            sourceByteIndexEnd: Int,
        ) {
            if (sourceByteIndexStart < 0 || sourceByteIndexEnd > sourceByteAvailability) {
                throw IndexOutOfBoundsException("sourceByteIndexStart[$sourceByteIndexStart], sourceByteIndexEnd[$sourceByteIndexEnd], sourceByteAvailability[$sourceByteAvailability]")
            }
            if (destByteIndexStart < 0 || destByteIndexEnd > destByteCapacity) {
                throw IndexOutOfBoundsException("destByteIndexStart[$destByteIndexStart], destByteIndexEnd[$destByteIndexEnd], destByteCapacity[$destByteCapacity]")
            }
            if (sourceByteIndexStart > sourceByteIndexEnd) {
                throw IllegalArgumentException("sourceByteIndexStart[$sourceByteIndexStart] > sourceByteIndexEnd[$sourceByteIndexEnd]")
            }
            if (destByteIndexStart > destByteIndexEnd) {
                throw IllegalArgumentException("destByteIndexStart[$destByteIndexStart] > destByteIndexEnd[$destByteIndexEnd]")
            }
            if (destByteIndexEnd - destByteIndexStart < sourceByteIndexEnd - sourceByteIndexStart) {
                throw IndexOutOfBoundsException("Not enough room in destination. destByteIndexStart[$destByteIndexStart], destByteIndexEnd[$destByteIndexEnd], sourceByteIndexStart[$sourceByteIndexStart], sourceByteIndexEnd[$sourceByteIndexEnd]")
            }
        }

        @JvmStatic
        private fun checkSourceRemainder(
            sourceByteIndexStart: Int,
            sourceByteIndexEnd: Int,
            numberSizeBytes: Int,
        ) {
            if ((sourceByteIndexEnd - sourceByteIndexStart) % numberSizeBytes != 0) {
                throw IndexOutOfBoundsException("Invalid remainder. Source indices must be a factor of $numberSizeBytes")
            }
        }
    }
}
