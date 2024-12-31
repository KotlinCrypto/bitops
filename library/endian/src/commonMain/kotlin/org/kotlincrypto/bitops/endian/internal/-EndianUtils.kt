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
internal value class B0 internal constructor(internal val b0: Byte)

@JvmInline
internal value class Lo internal constructor(internal val lo: Int)

internal inline fun Lo.toLong(hi: Int): Long {
    return  ((hi.toLong() and 0xffffffff) shl 32) or
            ((lo.toLong() and 0xffffffff)       )
}

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

internal inline fun ByteArray.packBEShort(target: Short, offset: Int): ByteArray {
    this[offset    ] = (target.toInt() ushr 8).toByte()
    this[offset + 1] = (target               ).toByte()
    return this
}

internal inline fun ByteArray.packLEShort(target: Short, offset: Int): ByteArray {
    this[offset    ] = (target               ).toByte()
    this[offset + 1] = (target.toInt() ushr 8).toByte()
    return this
}

internal inline fun ByteArray.packBEInt(target: Int, offset: Int): ByteArray {
    this[offset    ] = (target ushr 24).toByte()
    this[offset + 1] = (target ushr 16).toByte()
    this[offset + 2] = (target ushr  8).toByte()
    this[offset + 3] = (target        ).toByte()
    return this
}

internal inline fun ByteArray.packLEInt(target: Int, offset: Int): ByteArray {
    this[offset    ] = (target        ).toByte()
    this[offset + 1] = (target ushr  8).toByte()
    this[offset + 2] = (target ushr 16).toByte()
    this[offset + 3] = (target ushr 24).toByte()
    return this
}

internal inline fun ByteArray.packBELong(target: Long, offset: Int): ByteArray {
    this[offset    ] = (target ushr 56).toByte()
    this[offset + 1] = (target ushr 48).toByte()
    this[offset + 2] = (target ushr 40).toByte()
    this[offset + 3] = (target ushr 32).toByte()
    this[offset + 4] = (target ushr 24).toByte()
    this[offset + 5] = (target ushr 16).toByte()
    this[offset + 6] = (target ushr  8).toByte()
    this[offset + 7] = (target        ).toByte()
    return this
}

internal inline fun ByteArray.packLELong(target: Long, offset: Int): ByteArray {
    this[offset    ] = (target        ).toByte()
    this[offset + 1] = (target ushr  8).toByte()
    this[offset + 2] = (target ushr 16).toByte()
    this[offset + 3] = (target ushr 24).toByte()
    this[offset + 4] = (target ushr 32).toByte()
    this[offset + 5] = (target ushr 40).toByte()
    this[offset + 6] = (target ushr 48).toByte()
    this[offset + 7] = (target ushr 56).toByte()
    return this
}

internal inline fun ByteArray.packAllElsePartial(
    offset: Int,
    startIndex: Int,
    endIndex: Int,
    sizeBytes: Int,
    packAll: ByteArray.() -> ByteArray,
    ushr: (bits: Int) -> Byte,
): ByteArray {
    return if (startIndex == 0 && endIndex == sizeBytes) packAll()
    else packPartial(offset, startIndex, endIndex, ushr)
}

internal inline fun ByteArray.packPartial(
    offset: Int,
    startIndex: Int,
    endIndex: Int,
    ushr: (bits: Int) -> Byte,
): ByteArray {
    var pos = offset
    for (i in startIndex..<endIndex) {
        this[pos++] = ushr(Byte.SIZE_BITS * i)
    }
    return this
}
