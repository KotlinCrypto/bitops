# Module endian

Utilities for working with Big or Little endian ordered bytes and numbers. The use of `inline` functions
are pervasive such that the `Endian.Big` & `Endian.Little` implementations are as performant as possible.

```kotlin
val b = ByteArray(Long.SIZE_BYTES) { -10 }

arrayOf(Endian.Big, Endian.Little).forEach { endianness ->
    println(endianness)

    // Short
    endianness.pack(source = 3883.toShort(), dest = b, destOffset = 0)
    println(b.toList())
    println(endianness.shortFrom(source = b, offset = 0))
    // Alternatively: endianness.shortOf(b[0], b[1])

    // Int
    endianness.pack(source = 3883541, dest = b, destOffset = 0)
    println(b.toList())
    println(endianness.intFrom(source = b, offset = 0))
    // Alternatively: endianness.intOf(b[0], b[1], b[2], b[3])

    // Long
    endianness.pack(source = 1948571948571333L, dest = b, destOffset = 0)
    println(b.toList())
    println(endianness.longFrom(source = b, offset = 0))
    // Alternatively: endianness.longOf(b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7])
    b.fill(-10)
    println("")
}

// Endian.Big
// [15, 43, -10, -10, -10, -10, -10, -10]
// 3883
// [0, 59, 66, 21, -10, -10, -10, -10]
// 3883541
// [0, 6, -20, 55, 66, -48, 90, -59]
// 1948571948571333
//
// Endian.Little
// [43, 15, -10, -10, -10, -10, -10, -10]
// 3883
// [21, 66, 59, 0, -10, -10, -10, -10]
// 3883541
// [-59, 90, -48, 66, 55, -20, 6, 0]
// 1948571948571333
```
