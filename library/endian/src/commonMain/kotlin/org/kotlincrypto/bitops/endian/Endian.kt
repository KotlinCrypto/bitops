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
@file:Suppress("KotlinRedundantDiagnosticSuppress", "NOTHING_TO_INLINE", "RemoveRedundantQualifierName")

package org.kotlincrypto.bitops.endian

import org.kotlincrypto.bitops.endian.internal.*
import kotlin.jvm.JvmStatic

/**
 * [Big] & [Little] endian utilities.
 * */
public sealed class Endian private constructor() {

    /**
     * Convert 2 bytes to a Short.
     *
     * e.g.
     *
     *     val number = 2702.toShort()
     *     val b = ByteArray(2) { -100 }
     *     println(b.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(number, b, 0)
     *     println(b.toList())
     *     // [10, -114]
     *     Endian.Big.shortOf(
     *         b[0],
     *         b[1],
     *     ).let { println(it) }
     *     // 2702
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0)
     *     println(b.toList())
     *     // [-114, 10]
     *     Endian.Little.shortOf(
     *         b[0],
     *         b[1],
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
     * Convert 4 bytes to an Int.
     *
     * e.g.
     *
     *     val number = -77362181
     *     val b = ByteArray(4) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 0)
     *     println(b.toList())
     *     // [-5, 99, -117, -5]
     *     Endian.Big.intOf(
     *         b[0],
     *         b[1],
     *         b[2],
     *         b[3],
     *     ).let { println(it) }
     *     // -77362181
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0)
     *     println(b.toList())
     *     // [-5, -117, 99, -5]
     *     Endian.Little.intOf(
     *         b[0],
     *         b[1],
     *         b[2],
     *         b[3],
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
     * Convert 8 bytes to a Long.
     *
     * e.g.
     *
     *     val number = 9223372034707292160L
     *     val b = ByteArray(8) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 0)
     *     println(b.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     Endian.Big.longOf(
     *         b[0],
     *         b[1],
     *         b[2],
     *         b[3],
     *         b[4],
     *         b[5],
     *         b[6],
     *         b[7],
     *     ).let { println(it) }
     *     // 9223372034707292160
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0)
     *     println(b.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     Endian.Little.longOf(
     *         b[0],
     *         b[1],
     *         b[2],
     *         b[3],
     *         b[4],
     *         b[5],
     *         b[6],
     *         b[7],
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
     * Convert 2 bytes from [source], starting at index [offset], to a Short.
     *
     * e.g.
     *
     *     val number = 2702.toShort()
     *     val b = ByteArray(2) { -100 }
     *     println(b.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(number, b, 0)
     *     println(b.toList())
     *     // [10, -114]
     *     println(Endian.Big.shortFrom(b, 0))
     *     // 2702
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0)
     *     println(b.toList())
     *     // [-114, 10]
     *     println(Endian.Little.shortFrom(b, 0))
     *     // 2702
     *
     * @see [shortOf]
     * @see [Big.beShortAt]
     * @see [Little.leShortAt]
     *
     * @param [source] The array to access bytes from.
     * @param [offset] The index to start at when retrieving bytes from [source].
     *
     * @throws [IndexOutOfBoundsException] when [offset] is inappropriate.
     * */
    public abstract fun shortFrom(
        source: ByteArray,
        offset: Int,
    ): Short

    /**
     * Convert 4 bytes from [source], starting at index [offset], to an Int.
     *
     * e.g.
     *
     *     val number = -77362181
     *     val b = ByteArray(4) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 0)
     *     println(b.toList())
     *     // [-5, 99, -117, -5]
     *     println(Endian.Big.intFrom(b, 0))
     *     // -77362181
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0)
     *     println(b.toList())
     *     // [-5, -117, 99, -5]
     *     println(Endian.Little.intFrom(b, 0))
     *     // -77362181
     *
     * @see [intOf]
     * @see [Big.beIntAt]
     * @see [Little.leIntAt]
     *
     * @param [source] The array to access bytes from.
     * @param [offset] The index to start at when retrieving bytes from [source].
     *
     * @throws [IndexOutOfBoundsException] when [offset] is inappropriate.
     * */
    public abstract fun intFrom(
        source: ByteArray,
        offset: Int,
    ): Int

    /**
     * Convert 8 bytes from [source], starting at index [offset], to a Long.
     *
     * e.g.
     *
     *     val number = 9223372034707292160L
     *     val b = ByteArray(8) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 0)
     *     println(b.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     println(Endian.Big.longFrom(b, 0))
     *     // 9223372034707292160
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0)
     *     println(b.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     println(Endian.Little.longFrom(b, 0))
     *     // 9223372034707292160
     *
     * @see [longOf]
     * @see [Big.beLongAt]
     * @see [Little.leLongAt]
     *
     * @param [source] The array to access bytes from.
     * @param [offset] The index to start at when retrieving bytes from [source].
     *
     * @throws [IndexOutOfBoundsException] when [offset] is inappropriate.
     * */
    public abstract fun longFrom(
        source: ByteArray,
        offset: Int,
    ): Long

    /**
     * Packs all 2 bytes of [target] Short into [dest], starting at index [destOffset].
     *
     * e.g.
     *
     *     val number = 2702.toShort()
     *     val b = ByteArray(4) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 1)
     *     println(b.toList())
     *     // [-100, 10, -114, -100]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 2)
     *     println(b.toList())
     *     // [-100, -100, -114, 10]
     *
     * @see [packUnsafe]
     * @see [Big.bePack]
     * @see [Little.lePack]
     *
     * @param [target] The Short to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public fun pack(
        target: Short,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray {
        checkPackParameters(
            sizeArray = dest.size,
            offset = destOffset,
            startIndex = 0,
            endIndex = Short.SIZE_BYTES,
            sizeBytes = Short.SIZE_BYTES,
        )
        return packUnsafe(target, dest, destOffset)
    }

    /**
     * Packs all 4 bytes of [target] Int into [dest], starting at index [destOffset].
     *
     * e.g.
     *
     *     val number = -77362181
     *     val b = ByteArray(6) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 1)
     *     println(b.toList())
     *     // [-100, -5, 99, -117, -5, -100]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 2)
     *     println(b.toList())
     *     // [-100, -100, -5, -117, 99, -5]
     *
     * @see [packUnsafe]
     * @see [Big.bePack]
     * @see [Little.lePack]
     *
     * @param [target] The Int to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public fun pack(
        target: Int,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray {
        checkPackParameters(
            sizeArray = dest.size,
            offset = destOffset,
            startIndex = 0,
            endIndex = Int.SIZE_BYTES,
            sizeBytes = Int.SIZE_BYTES,
        )
        return packUnsafe(target, dest, destOffset)
    }

    /**
     * Packs all 8 bytes of [target] Long into [dest], starting at index [destOffset].
     *
     * e.g.
     *
     *     val number = 9223372034707292160L
     *     val b = ByteArray(11) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 1)
     *     println(b.toList())
     *     // [-100, 127, -1, -1, -1, -128, 0, 0, 0, -100, -100]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 3)
     *     println(b.toList())
     *     // [-100, -100, -100, 0, 0, 0, -128, -1, -1, -1, 127]
     *
     * @see [packUnsafe]
     * @see [Big.bePack]
     * @see [Little.lePack]
     *
     * @param [target] The Long to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public fun pack(
        target: Long,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray {
        checkPackParameters(
            sizeArray = dest.size,
            offset = destOffset,
            startIndex = 0,
            endIndex = Long.SIZE_BYTES,
            sizeBytes = Long.SIZE_BYTES,
        )
        return packUnsafe(target, dest, destOffset)
    }

    /**
     * Packs bytes of [target] Short from [startIndex] (inclusive) to [endIndex]
     * (exclusive) into [dest], starting at [destOffset]. If [startIndex] is 0
     * and [endIndex] is [Short.SIZE_BYTES], the more performant [pack] function
     * will be utilized.
     *
     * e.g.
     *
     *     val number = 2702.toShort()
     *     val b = ByteArray(2) { -100 }
     *     println(b.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.pack(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [10, -114]
     *     b.fill(-100)
     *
     *     Endian.Big.pack(number, b, 1, startIndex = 0, endIndex = 1)
     *     println(b.toList())
     *     // [-100, 10]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [-114, 10]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 1, startIndex = 1)
     *     println(b.toList())
     *     // [-100, 10]
     *
     * @see [packUnsafe]
     * @see [Big.bePack]
     * @see [Little.lePack]
     *
     * @param [target] The Short to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [startIndex] The beginning (inclusive) of the [target] subrange to unpack.
     * @param [endIndex] The end (exclusive) of the [target] subrange to unpack,
     *   [Short.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when
     *   [startIndex] or [endIndex] are out of range of the array indices, or when
     *   `startIndex > endIndex`.
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public fun pack(
        target: Short,
        dest: ByteArray,
        destOffset: Int,
        startIndex: Int,
        endIndex: Int = Short.SIZE_BYTES,
    ): ByteArray {
        checkPackParameters(
            sizeArray = dest.size,
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Short.SIZE_BYTES,
        )
        return packUnsafe(target, dest, destOffset, startIndex, endIndex)
    }

    /**
     * Packs bytes of [target] Int from [startIndex] (inclusive) to [endIndex]
     * (exclusive) into [dest], starting at [destOffset]. If [startIndex] is 0
     * and [endIndex] is [Int.SIZE_BYTES], the more performant [pack] function
     * will be utilized.
     *
     * e.g.
     *
     *     val number = -77362181
     *     val b = ByteArray(4) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [-5, 99, -117, -5]
     *     b.fill(-100)
     *
     *     Endian.Big.pack(number, b, 2, startIndex = 1, endIndex = 3)
     *     println(b.toList())
     *     // [-100, -100, 99, -117]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [-5, -117, 99, -5]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 1, startIndex = 3)
     *     println(b.toList())
     *     // [-100, -5, -100, -100]
     *
     * @see [packUnsafe]
     * @see [Big.bePack]
     * @see [Little.lePack]
     *
     * @param [target] The Int to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [startIndex] The beginning (inclusive) of the [target] subrange to unpack.
     * @param [endIndex] The end (exclusive) of the [target] subrange to unpack,
     *   [Int.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when
     *   [startIndex] or [endIndex] are out of range of the array indices, or when
     *   `startIndex > endIndex`.
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public fun pack(
        target: Int,
        dest: ByteArray,
        destOffset: Int,
        startIndex: Int,
        endIndex: Int = Int.SIZE_BYTES,
    ): ByteArray {
        checkPackParameters(
            sizeArray = dest.size,
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Int.SIZE_BYTES,
        )
        return packUnsafe(target, dest, destOffset, startIndex, endIndex)
    }

    /**
     * Packs bytes of [target] Long from [startIndex] (inclusive) to [endIndex]
     * (exclusive) into [dest], starting at [destOffset]. If [startIndex] is 0
     * and [endIndex] is [Long.SIZE_BYTES], the more performant [pack] function
     * will be utilized.
     *
     * e.g.
     *
     *     val number = 9223372034707292160L
     *     val b = ByteArray(8) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.pack(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     b.fill(-100)
     *
     *     Endian.Big.pack(number, b, 2, startIndex = 1, endIndex = 7)
     *     println(b.toList())
     *     // [-100, -100, -1, -1, -1, -128, 0, 0]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     b.fill(-100)
     *
     *     Endian.Little.pack(number, b, 1, startIndex = 3)
     *     println(b.toList())
     *     // [-100, -128, -1, -1, -1, 127, -100, -100]
     *
     * @see [packUnsafe]
     * @see [Big.bePack]
     * @see [Little.lePack]
     *
     * @param [target] The Long to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [startIndex] The beginning (inclusive) of the [target] subrange to unpack.
     * @param [endIndex] The end (exclusive) of the [target] subrange to unpack,
     *   [Long.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] or [IllegalArgumentException] when
     *   [startIndex] or [endIndex] are out of range of the array indices, or when
     *   `startIndex > endIndex`.
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public fun pack(
        target: Long,
        dest: ByteArray,
        destOffset: Int,
        startIndex: Int,
        endIndex: Int = Long.SIZE_BYTES,
    ): ByteArray {
        checkPackParameters(
            sizeArray = dest.size,
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Long.SIZE_BYTES,
        )
        return packUnsafe(target, dest, destOffset, startIndex, endIndex)
    }

    /**
     * Packs all 2 bytes of [target] Short into [dest], starting at index [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before
     * altering the [dest], but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val number = 2702.toShort()
     *     val b = ByteArray(4) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(number, b, 1)
     *     println(b.toList())
     *     // [-100, 10, -114, -100]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 2)
     *     println(b.toList())
     *     // [-100, -100, -114, 10]
     *
     * @see [Big.bePackUnsafe]
     * @see [Little.lePackUnsafe]
     *
     * @param [target] The Short to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public abstract fun packUnsafe(
        target: Short,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray

    /**
     * Packs all 4 bytes of [target] Int into [dest], starting at index [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before
     * altering the [dest], but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val number = -77362181
     *     val b = ByteArray(6) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(number, b, 1)
     *     println(b.toList())
     *     // [-100, -5, 99, -117, -5, -100]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 2)
     *     println(b.toList())
     *     // [-100, -100, -5, -117, 99, -5]
     *
     * @see [Big.bePackUnsafe]
     * @see [Little.lePackUnsafe]
     *
     * @param [target] The Int to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public abstract fun packUnsafe(
        target: Int,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray

    /**
     * Packs all 8 bytes of [target] Long into [dest], starting at index [destOffset].
     *
     * **NOTE:** This function does not check input parameters for correctness before
     * altering the [dest], but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val number = 9223372034707292160L
     *     val b = ByteArray(11) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(number, b, 1)
     *     println(b.toList())
     *     // [-100, 127, -1, -1, -1, -128, 0, 0, 0, -100, -100]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 3)
     *     println(b.toList())
     *     // [-100, -100, -100, 0, 0, 0, -128, -1, -1, -1, 127]
     *
     * @see [Big.bePackUnsafe]
     * @see [Little.lePackUnsafe]
     *
     * @param [target] The Long to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] when subrange will not fit into the
     *   [dest] array starting at the specified [destOffset], or when
     *   that index is out of the [dest] array indices range.
     * */
    public abstract fun packUnsafe(
        target: Long,
        dest: ByteArray,
        destOffset: Int,
    ): ByteArray

    /**
     * Packs bytes of [target] Short from [startIndex] (inclusive) to [endIndex]
     * (exclusive) into [dest], starting at [destOffset]. If [startIndex] is 0
     * and [endIndex] is [Short.SIZE_BYTES], the more performant [pack] function
     * will be utilized.
     *
     * **NOTE:** This function does not check input parameters for correctness before
     * altering the [dest], but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val number = 2702.toShort()
     *     val b = ByteArray(2) { -100 }
     *     println(b.toList())
     *     // [-100, -100]
     *
     *     Endian.Big.packUnsafe(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [10, -114]
     *     b.fill(-100)
     *
     *     Endian.Big.packUnsafe(number, b, 1, startIndex = 0, endIndex = 1)
     *     println(b.toList())
     *     // [-100, 10]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [-114, 10]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 1, startIndex = 1)
     *     println(b.toList())
     *     // [-100, 10]
     *
     * @see [Big.bePackUnsafe]
     * @see [Little.lePackUnsafe]
     *
     * @param [target] The Short to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [startIndex] The beginning (inclusive) of the [target] subrange to unpack.
     * @param [endIndex] The end (exclusive) of the [target] subrange to unpack,
     *   [Short.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if parameters are incorrect and an invalid
     *   index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        target: Short,
        dest: ByteArray,
        destOffset: Int,
        startIndex: Int,
        endIndex: Int = Short.SIZE_BYTES,
    ): ByteArray

    /**
     * Packs bytes of [target] Int from [startIndex] (inclusive) to [endIndex]
     * (exclusive) into [dest], starting at [destOffset]. If [startIndex] is 0
     * and [endIndex] is [Int.SIZE_BYTES], the more performant [pack] function
     * will be utilized.
     *
     * **NOTE:** This function does not check input parameters for correctness before
     * altering the [dest], but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val number = -77362181
     *     val b = ByteArray(4) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [-5, 99, -117, -5]
     *     b.fill(-100)
     *
     *     Endian.Big.packUnsafe(number, b, 2, startIndex = 1, endIndex = 3)
     *     println(b.toList())
     *     // [-100, -100, 99, -117]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [-5, -117, 99, -5]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 1, startIndex = 3)
     *     println(b.toList())
     *     // [-100, -5, -100, -100]
     *
     * @see [Big.bePackUnsafe]
     * @see [Little.lePackUnsafe]
     *
     * @param [target] The Int to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [startIndex] The beginning (inclusive) of the [target] subrange to unpack.
     * @param [endIndex] The end (exclusive) of the [target] subrange to unpack,
     *   [Int.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if parameters are incorrect and an invalid
     *   index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        target: Int,
        dest: ByteArray,
        destOffset: Int,
        startIndex: Int,
        endIndex: Int = Int.SIZE_BYTES,
    ): ByteArray

    /**
     * Packs bytes of [target] Long from [startIndex] (inclusive) to [endIndex]
     * (exclusive) into [dest], starting at [destOffset]. If [startIndex] is 0
     * and [endIndex] is [Long.SIZE_BYTES], the more performant [pack] function
     * will be utilized.
     *
     * **NOTE:** This function does not check input parameters for correctness before
     * altering the [dest], but is faster than [pack] because of it.
     *
     * e.g.
     *
     *     val number = 9223372034707292160L
     *     val b = ByteArray(8) { -100 }
     *     println(b.toList())
     *     // [-100, -100, -100, -100, -100, -100, -100, -100]
     *
     *     Endian.Big.packUnsafe(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [127, -1, -1, -1, -128, 0, 0, 0]
     *     b.fill(-100)
     *
     *     Endian.Big.packUnsafe(number, b, 2, startIndex = 1, endIndex = 7)
     *     println(b.toList())
     *     // [-100, -100, -1, -1, -1, -128, 0, 0]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 0, startIndex = 0)
     *     println(b.toList())
     *     // [0, 0, 0, -128, -1, -1, -1, 127]
     *     b.fill(-100)
     *
     *     Endian.Little.packUnsafe(number, b, 1, startIndex = 3)
     *     println(b.toList())
     *     // [-100, -128, -1, -1, -1, 127, -100, -100]
     *
     * @see [Big.bePackUnsafe]
     * @see [Little.lePackUnsafe]
     *
     * @param [target] The Long to unpack bytes from.
     * @param [dest] The array to pack bytes into.
     * @param [destOffset] The position in [dest] to start packing.
     * @param [startIndex] The beginning (inclusive) of the [target] subrange to unpack.
     * @param [endIndex] The end (exclusive) of the [target] subrange to unpack,
     *   [Long.SIZE_BYTES] by default.
     *
     * @return The [dest] array
     *
     * @throws [IndexOutOfBoundsException] if parameters are incorrect and an invalid
     *   index in [dest] is accessed.
     * */
    public abstract fun packUnsafe(
        target: Long,
        dest: ByteArray,
        destOffset: Int,
        startIndex: Int,
        endIndex: Int = Long.SIZE_BYTES,
    ): ByteArray

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
        public inline fun ByteArray.bePack(
            target: Short,
            offset: Int,
        ): ByteArray = Big.pack(target, this, offset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePack(
            target: Int,
            offset: Int,
        ): ByteArray = Big.pack(target, this, offset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePack(
            target: Long,
            offset: Int,
        ): ByteArray = Big.pack(target, this, offset)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePack(
            target: Short,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Short.SIZE_BYTES,
        ): ByteArray = Big.pack(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePack(
            target: Int,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Int.SIZE_BYTES,
        ): ByteArray = Big.pack(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Big.pack] */
        @JvmStatic
        public inline fun ByteArray.bePack(
            target: Long,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Long.SIZE_BYTES,
        ): ByteArray = Big.pack(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackUnsafe(
            target: Short,
            offset: Int,
        ): ByteArray = Big.packUnsafe(target, this, offset)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackUnsafe(
            target: Int,
            offset: Int,
        ): ByteArray = Big.packUnsafe(target, this, offset)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackUnsafe(
            target: Long,
            offset: Int,
        ): ByteArray = Big.packUnsafe(target, this, offset)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackUnsafe(
            target: Short,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Short.SIZE_BYTES,
        ): ByteArray = Big.packUnsafe(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackUnsafe(
            target: Int,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Int.SIZE_BYTES,
        ): ByteArray = Big.packUnsafe(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Big.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.bePackUnsafe(
            target: Long,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Long.SIZE_BYTES,
        ): ByteArray = Big.packUnsafe(target, this, offset, startIndex, endIndex)

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
        ): Short = B0(source[offset]).toBEShort(
            source[offset + 1],
        )

        public override fun intFrom(
            source: ByteArray,
            offset: Int,
        ): Int = B0(source[offset]).toBEInt(
            source[offset + 1],
            source[offset + 2],
            source[offset + 3],
        )

        public override fun longFrom(
            source: ByteArray,
            offset: Int
        ): Long = B0(source[offset]).toBELong(
            source[offset + 1],
            source[offset + 2],
            source[offset + 3],
            source[offset + 4],
            source[offset + 5],
            source[offset + 6],
            source[offset + 7],
        )

        public override fun packUnsafe(
            target: Short,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packBEShort(target, destOffset)

        public override fun packUnsafe(
            target: Int,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packBEInt(target, destOffset)

        public override fun packUnsafe(
            target: Long,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packBELong(target, destOffset)

        public override fun packUnsafe(
            target: Short,
            dest: ByteArray,
            destOffset: Int,
            startIndex: Int,
            endIndex: Int, // = Short.SIZE_BYTES
        ): ByteArray = dest.packAllElsePartial(
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Short.SIZE_BYTES,
            packAll = { packBEShort(target, destOffset) },
            ushr = { bits -> (target.toInt() ushr (8 - bits)).toByte() },
        )

        public override fun packUnsafe(
            target: Int,
            dest: ByteArray,
            destOffset: Int,
            startIndex: Int,
            endIndex: Int, // = Int.SIZE_BYTES
        ): ByteArray = dest.packAllElsePartial(
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Int.SIZE_BYTES,
            packAll = { packBEInt(target, destOffset) },
            ushr = { bits -> (target ushr (24 - bits)).toByte() },
        )

        public override fun packUnsafe(
            target: Long,
            dest: ByteArray,
            destOffset: Int,
            startIndex: Int,
            endIndex: Int, // = Long.SIZE_BYTES
        ): ByteArray = dest.packAllElsePartial(
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Long.SIZE_BYTES,
            packAll = { packBELong(target, destOffset) },
            ushr = { bits -> (target ushr (56 - bits)).toByte() },
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
        public inline fun ByteArray.lePack(
            target: Short,
            offset: Int,
        ): ByteArray = Little.pack(target, this, offset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePack(
            target: Int,
            offset: Int,
        ): ByteArray = Little.pack(target, this, offset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePack(
            target: Long,
            offset: Int,
        ): ByteArray = Little.pack(target, this, offset)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePack(
            target: Short,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Short.SIZE_BYTES,
        ): ByteArray = Little.pack(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePack(
            target: Int,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Int.SIZE_BYTES,
        ): ByteArray = Little.pack(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Little.pack] */
        @JvmStatic
        public inline fun ByteArray.lePack(
            target: Long,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Long.SIZE_BYTES,
        ): ByteArray = Little.pack(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackUnsafe(
            target: Short,
            offset: Int,
        ): ByteArray = Little.packUnsafe(target, this, offset)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackUnsafe(
            target: Int,
            offset: Int,
        ): ByteArray = Little.packUnsafe(target, this, offset)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackUnsafe(
            target: Long,
            offset: Int,
        ): ByteArray = Little.packUnsafe(target, this, offset)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackUnsafe(
            target: Short,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Short.SIZE_BYTES,
        ): ByteArray = Little.packUnsafe(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackUnsafe(
            target: Int,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Int.SIZE_BYTES,
        ): ByteArray = Little.packUnsafe(target, this, offset, startIndex, endIndex)

        /** Syntactic Sugar. See [Endian.Little.packUnsafe] */
        @JvmStatic
        public inline fun ByteArray.lePackUnsafe(
            target: Long,
            offset: Int,
            startIndex: Int,
            endIndex: Int = Long.SIZE_BYTES,
        ): ByteArray = Little.packUnsafe(target, this, offset, startIndex, endIndex)

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
        ): Short = B0(source[offset]).toLEShort(
            source[offset + 1],
        )

        public override fun intFrom(
            source: ByteArray,
            offset: Int,
        ): Int = B0(source[offset]).toLEInt(
            source[offset + 1],
            source[offset + 2],
            source[offset + 3],
        )

        public override fun longFrom(
            source: ByteArray,
            offset: Int
        ): Long = B0(source[offset]).toLELong(
            source[offset + 1],
            source[offset + 2],
            source[offset + 3],
            source[offset + 4],
            source[offset + 5],
            source[offset + 6],
            source[offset + 7],
        )

        public override fun packUnsafe(
            target: Short,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packLEShort(target, destOffset)

        public override fun packUnsafe(
            target: Int,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packLEInt(target, destOffset)

        public override fun packUnsafe(
            target: Long,
            dest: ByteArray,
            destOffset: Int,
        ): ByteArray = dest.packLELong(target, destOffset)

        public override fun packUnsafe(
            target: Short,
            dest: ByteArray,
            destOffset: Int,
            startIndex: Int,
            endIndex: Int, // = Short.SIZE_BYTES
        ): ByteArray = dest.packAllElsePartial(
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Short.SIZE_BYTES,
            packAll = { packLEShort(target, destOffset) },
            ushr = { bits -> (target.toInt() ushr bits).toByte() },
        )

        public override fun packUnsafe(
            target: Int,
            dest: ByteArray,
            destOffset: Int,
            startIndex: Int,
            endIndex: Int, // = Int.SIZE_BYTES
        ): ByteArray = dest.packAllElsePartial(
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Int.SIZE_BYTES,
            packAll = { packLEInt(target, destOffset) },
            ushr = { bits -> (target ushr bits).toByte() },
        )

        public override fun packUnsafe(
            target: Long,
            dest: ByteArray,
            destOffset: Int,
            startIndex: Int,
            endIndex: Int, // = Long.SIZE_BYTES
        ): ByteArray = dest.packAllElsePartial(
            offset = destOffset,
            startIndex = startIndex,
            endIndex = endIndex,
            sizeBytes = Long.SIZE_BYTES,
            packAll = { packLELong(target, destOffset) },
            ushr = { bits -> (target ushr bits).toByte() },
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
            sizeArray: Int,
            offset: Int,
            startIndex: Int,
            endIndex: Int,
            sizeBytes: Int,
        ) {
            if (startIndex < 0 || endIndex > sizeBytes) {
                throw IndexOutOfBoundsException("startIndex[$startIndex], endIndex[$endIndex], sizeBytes[$sizeBytes]")
            }
            if (startIndex > endIndex) {
                throw IllegalArgumentException("startIndex[$startIndex] > endIndex[$endIndex]")
            }
            if (offset < 0 || offset > sizeArray) {
                throw IndexOutOfBoundsException("offset[$offset], sizeArray[$sizeArray]")
            }
            if (sizeArray - offset < endIndex - startIndex) {
                throw IndexOutOfBoundsException("Not enough room. sizeArray[$sizeArray], offset[$offset], startIndex[$startIndex], endIndex[$endIndex], sizeBytes[$sizeBytes]")
            }
        }
    }
}
