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
@file:Suppress("KotlinRedundantDiagnosticSuppress", "NOTHING_TO_INLINE", "DeprecatedCallableAddReplaceWith")

package org.kotlincrypto.bitops.endian

import org.kotlincrypto.bitops.endian.internal.*

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.longOf(
    lo: Int,
    hi: Int,
): Long = Lo(lo).toLong(hi = hi)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlineShortOf(
    b0: Byte,
    b1: Byte,
): Short = B0(b0).toBEShort(b1)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlineIntOf(
    b0: Byte,
    b1: Byte,
    b2: Byte,
    b3: Byte,
): Int = B0(b0).toBEInt(b1, b2, b3)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlineLongOf(
    b0: Byte,
    b1: Byte,
    b2: Byte,
    b3: Byte,
    b4: Byte,
    b5: Byte,
    b6: Byte,
    b7: Byte,
): Long = B0(b0).toBELong(b1, b2, b3, b4, b5, b6, b7)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlineShortOf(
    b0: Byte,
    b1: Byte,
): Short = B0(b0).toLEShort(b1)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlineIntOf(
    b0: Byte,
    b1: Byte,
    b2: Byte,
    b3: Byte,
): Int = B0(b0).toLEInt(b1, b2, b3)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlineLongOf(
    b0: Byte,
    b1: Byte,
    b2: Byte,
    b3: Byte,
    b4: Byte,
    b5: Byte,
    b6: Byte,
    b7: Byte,
): Long = B0(b0).toLELong(b1, b2, b3, b4, b5, b6, b7)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlineShortFrom(
    source: ByteArray,
    offset: Int,
): Short = B0(source[offset]).toBEShort(
    source[offset + 1],
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlineIntFrom(
    source: ByteArray,
    offset: Int,
): Int = B0(source[offset]).toBEInt(
    source[offset + 1],
    source[offset + 2],
    source[offset + 3],
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlineLongFrom(
    source: ByteArray,
    offset: Int,
): Long = B0(source[offset]).toBELong(
    source[offset + 1],
    source[offset + 2],
    source[offset + 3],
    source[offset + 4],
    source[offset + 5],
    source[offset + 6],
    source[offset + 7],
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlineShortFrom(
    source: ByteArray,
    offset: Int,
): Short = B0(source[offset]).toLEShort(
    source[offset + 1],
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlineIntFrom(
    source: ByteArray,
    offset: Int,
): Int = B0(source[offset]).toLEInt(
    source[offset + 1],
    source[offset + 2],
    source[offset + 3],
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlineLongFrom(
    source: ByteArray,
    offset: Int,
): Long = B0(source[offset]).toLELong(
    source[offset + 1],
    source[offset + 2],
    source[offset + 3],
    source[offset + 4],
    source[offset + 5],
    source[offset + 6],
    source[offset + 7],
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlinePackUnsafe(
    target: Short,
    dest: ByteArray,
    destOffset: Int,
): ByteArray = dest.packBEShort(target, destOffset)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlinePackUnsafe(
    target: Int,
    dest: ByteArray,
    destOffset: Int,
): ByteArray = dest.packBEInt(target, destOffset)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlinePackUnsafe(
    target: Long,
    dest: ByteArray,
    destOffset: Int,
): ByteArray = dest.packBELong(target, destOffset)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlinePackUnsafe(
    target: Short,
    dest: ByteArray,
    destOffset: Int,
): ByteArray = dest.packLEShort(target, destOffset)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlinePackUnsafe(
    target: Int,
    dest: ByteArray,
    destOffset: Int,
): ByteArray = dest.packLEInt(target, destOffset)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlinePackUnsafe(
    target: Long,
    dest: ByteArray,
    destOffset: Int,
): ByteArray = dest.packLELong(target, destOffset)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlinePackUnsafe(
    target: Short,
    dest: ByteArray,
    destOffset: Int,
    startIndex: Int,
    endIndex: Int = Short.SIZE_BYTES,
): ByteArray = dest.packAllElsePartial(
    offset = destOffset,
    startIndex = startIndex,
    endIndex = endIndex,
    sizeBytes = Short.SIZE_BYTES,
    packAll = { packBEShort(target, destOffset) },
    ushr = { bits -> (target.toInt() ushr (8 - bits)).toByte() },
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlinePackUnsafe(
    target: Int,
    dest: ByteArray,
    destOffset: Int,
    startIndex: Int,
    endIndex: Int = Int.SIZE_BYTES,
): ByteArray = dest.packAllElsePartial(
    offset = destOffset,
    startIndex = startIndex,
    endIndex = endIndex,
    sizeBytes = Int.SIZE_BYTES,
    packAll = { packBEInt(target, destOffset) },
    ushr = { bits -> (target ushr (24 - bits)).toByte() },
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.beInlinePackUnsafe(
    target: Long,
    dest: ByteArray,
    destOffset: Int,
    startIndex: Int,
    endIndex: Int = Long.SIZE_BYTES,
): ByteArray = dest.packAllElsePartial(
    offset = destOffset,
    startIndex = startIndex,
    endIndex = endIndex,
    sizeBytes = Long.SIZE_BYTES,
    packAll = { packBELong(target, destOffset) },
    ushr = { bits -> (target ushr (56 - bits)).toByte() },
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlinePackUnsafe(
    target: Short,
    dest: ByteArray,
    destOffset: Int,
    startIndex: Int,
    endIndex: Int = Short.SIZE_BYTES,
): ByteArray = dest.packAllElsePartial(
    offset = destOffset,
    startIndex = startIndex,
    endIndex = endIndex,
    sizeBytes = Short.SIZE_BYTES,
    packAll = { packLEShort(target, destOffset) },
    ushr = { bits -> (target.toInt() ushr bits).toByte() },
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlinePackUnsafe(
    target: Int,
    dest: ByteArray,
    destOffset: Int,
    startIndex: Int,
    endIndex: Int = Int.SIZE_BYTES,
): ByteArray = dest.packAllElsePartial(
    offset = destOffset,
    startIndex = startIndex,
    endIndex = endIndex,
    sizeBytes = Int.SIZE_BYTES,
    packAll = { packLEInt(target, destOffset) },
    ushr = { bits -> (target ushr bits).toByte() },
)

/** @suppress */
@InternalEndianApi
@Deprecated("Not meant for public consumption", level = DeprecationLevel.ERROR)
public inline fun Endian.Companion.leInlinePackUnsafe(
    target: Long,
    dest: ByteArray,
    destOffset: Int,
    startIndex: Int,
    endIndex: Int = Long.SIZE_BYTES,
): ByteArray = dest.packAllElsePartial(
    offset = destOffset,
    startIndex = startIndex,
    endIndex = endIndex,
    sizeBytes = Long.SIZE_BYTES,
    packAll = { packLELong(target, destOffset) },
    ushr = { bits -> (target ushr bits).toByte() },
)
