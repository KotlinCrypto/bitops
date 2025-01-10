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
@file:Suppress("KotlinRedundantDiagnosticSuppress", "NOTHING_TO_INLINE")

package org.kotlincrypto.bitops.endian.internal

import kotlin.jvm.JvmInline

@JvmInline
internal value class Lo internal constructor(internal val lo: Int)

internal inline fun Lo.toLong(hi: Int): Long {
    return  ((hi.toLong() and 0xffffffff) shl 32) or
            ((lo.toLong() and 0xffffffff)       )
}

@JvmInline
internal value class B0 internal constructor(internal val b0: Byte)

internal inline fun B0.toBEShort(b1: Byte): Short {
    return  (
                ((b0.toInt() and 0xff) shl  8) or
                ((b1.toInt() and 0xff)       )
            ).toShort()
}

internal inline fun B0.toLEShort(b1: Byte): Short {
    return  (
                ((b0.toInt() and 0xff)       ) or
                ((b1.toInt() and 0xff) shl  8)
            ).toShort()
}

internal inline fun B0.toBEInt(b1: Byte, b2: Byte, b3: Byte): Int {
    return  ((b0.toInt()         ) shl 24) or
            ((b1.toInt() and 0xff) shl 16) or
            ((b2.toInt() and 0xff) shl  8) or
            ((b3.toInt() and 0xff)       )
}

internal inline fun B0.toLEInt(b1: Byte, b2: Byte, b3: Byte): Int {
    return  ((b0.toInt() and 0xff)       ) or
            ((b1.toInt() and 0xff) shl  8) or
            ((b2.toInt() and 0xff) shl 16) or
            ((b3.toInt()         ) shl 24)
}

internal inline fun B0.toBELong(b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte): Long {
    return Lo(B0(b4).toBEInt(b5, b6, b7)).toLong(hi = this.toBEInt(b1, b2, b3))
}

internal inline fun B0.toLELong(b1: Byte, b2: Byte, b3: Byte, b4: Byte, b5: Byte, b6: Byte, b7: Byte): Long {
    return Lo(this.toLEInt(b1, b2, b3)).toLong(hi = B0(b4).toLEInt(b5, b6, b7))
}

internal inline fun ByteArray.packBEShort(source: Short, offset: Int): ByteArray {
    this[offset    ] = (source.toInt() ushr 8).toByte()
    this[offset + 1] = (source               ).toByte()
    return this
}

internal inline fun ByteArray.packLEShort(source: Short, offset: Int): ByteArray {
    this[offset    ] = (source               ).toByte()
    this[offset + 1] = (source.toInt() ushr 8).toByte()
    return this
}

internal inline fun ByteArray.packBEInt(source: Int, offset: Int): ByteArray {
    this[offset    ] = (source ushr 24).toByte()
    this[offset + 1] = (source ushr 16).toByte()
    this[offset + 2] = (source ushr  8).toByte()
    this[offset + 3] = (source        ).toByte()
    return this
}

internal inline fun ByteArray.packLEInt(source: Int, offset: Int): ByteArray {
    this[offset    ] = (source        ).toByte()
    this[offset + 1] = (source ushr  8).toByte()
    this[offset + 2] = (source ushr 16).toByte()
    this[offset + 3] = (source ushr 24).toByte()
    return this
}

internal inline fun ByteArray.packBELong(source: Long, offset: Int): ByteArray {
    this[offset    ] = (source ushr 56).toByte()
    this[offset + 1] = (source ushr 48).toByte()
    this[offset + 2] = (source ushr 40).toByte()
    this[offset + 3] = (source ushr 32).toByte()
    this[offset + 4] = (source ushr 24).toByte()
    this[offset + 5] = (source ushr 16).toByte()
    this[offset + 6] = (source ushr  8).toByte()
    this[offset + 7] = (source        ).toByte()
    return this
}

internal inline fun ByteArray.packLELong(source: Long, offset: Int): ByteArray {
    this[offset    ] = (source        ).toByte()
    this[offset + 1] = (source ushr  8).toByte()
    this[offset + 2] = (source ushr 16).toByte()
    this[offset + 3] = (source ushr 24).toByte()
    this[offset + 4] = (source ushr 32).toByte()
    this[offset + 5] = (source ushr 40).toByte()
    this[offset + 6] = (source ushr 48).toByte()
    this[offset + 7] = (source ushr 56).toByte()
    return this
}

internal inline fun ByteArray.unpackBEShort(offset: Int): Short = B0(this[offset]).toBEShort(
    this[offset + 1],
)

internal inline fun ByteArray.unpackLEShort(offset: Int): Short = B0(this[offset]).toLEShort(
    this[offset + 1],
)

internal inline fun ByteArray.unpackBEInt(offset: Int): Int = B0(this[offset]).toBEInt(
    this[offset + 1],
    this[offset + 2],
    this[offset + 3],
)

internal inline fun ByteArray.unpackLEInt(offset: Int): Int = B0(this[offset]).toLEInt(
    this[offset + 1],
    this[offset + 2],
    this[offset + 3],
)

internal inline fun ByteArray.unpackBELong(offset: Int): Long = B0(this[offset]).toBELong(
    this[offset + 1],
    this[offset + 2],
    this[offset + 3],
    this[offset + 4],
    this[offset + 5],
    this[offset + 6],
    this[offset + 7],
)

internal inline fun ByteArray.unpackLELong(offset: Int): Long = B0(this[offset]).toLELong(
    this[offset + 1],
    this[offset + 2],
    this[offset + 3],
    this[offset + 4],
    this[offset + 5],
    this[offset + 6],
    this[offset + 7],
)

internal inline fun ByteArray.packNumberAllElsePartial(
    destOffset: Int,
    sourceIndexStart: Int,
    sourceIndexEnd: Int,
    numberSizeBytes: Int,
    packNumber: ByteArray.() -> ByteArray,
    ushr: (bits: Int) -> Byte,
): ByteArray {
    // Check endIndex first
    return if (sourceIndexEnd == numberSizeBytes && sourceIndexStart == 0) packNumber(this)
    else packNumberPartial(destOffset, sourceIndexStart, sourceIndexEnd, ushr)
}

internal inline fun ByteArray.packNumberPartial(
    destOffset: Int,
    sourceIndexStart: Int,
    sourceIndexEnd: Int,
    ushr: (bits: Int) -> Byte,
): ByteArray {
    var destPos = destOffset
    for (i in sourceIndexStart..<sourceIndexEnd) {
        this[destPos++] = ushr(Byte.SIZE_BITS * i)
    }
    return this
}

internal inline fun ByteArray.packArray(
    destOffset: Int,
    sourceIndexStart: Int,
    sourceIndexEnd: Int,
    numberSizeBytes: Int,
    packNumber: ByteArray.(sourcePos: Int, destPos: Int) -> ByteArray,
): ByteArray {
    var destPos = destOffset
    var sourcePos = sourceIndexStart
    while (sourcePos < sourceIndexEnd) {
        packNumber(this, sourcePos++, destPos)
        destPos += numberSizeBytes
    }
    return this
}

internal inline fun <TypedArray: Any> TypedArray.packArray(
    source: ByteArray,
    destOffset: Int,
    sourceIndexStart: Int,
    sourceIndexEnd: Int,
    numberSizeBytes: Int,
    unpackNumber: ByteArray.(sourcePos: Int, destPos: Int) -> Unit,
): TypedArray {
    var destPos = destOffset
    var sourcePos = sourceIndexStart
    while (sourcePos < sourceIndexEnd) {
        unpackNumber(source, sourcePos, destPos++)
        sourcePos += numberSizeBytes
    }
    return this
}
