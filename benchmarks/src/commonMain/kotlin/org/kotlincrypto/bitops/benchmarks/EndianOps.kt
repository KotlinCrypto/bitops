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
@file:Suppress("unused")

package org.kotlincrypto.bitops.benchmarks

import kotlinx.benchmark.*
import org.kotlincrypto.bitops.endian.Endian
import kotlin.random.Random

abstract class EndianBenchmarkBase(private val endian: Endian) {

    private val bytes = Random.Default.nextBytes(Int.SIZE_BYTES * 4)
    private val ints = IntArray(bytes.size / 4) { Random.Default.nextInt() }

    @Benchmark
    fun intFrom() {
        endian.intFrom(source = bytes, offset = 0)
    }

    @Benchmark
    fun intOf() {
        endian.intOf(-42, 88, 42, -88)
    }

    @Benchmark
    fun packAll() {
        endian.pack(-42, bytes, 0)
    }

    @Benchmark
    fun packAllUnsafe() {
        endian.packUnsafe(-42, bytes, 0)
    }

    @Benchmark
    fun packArray() {
        endian.pack(source = bytes, dest = ints, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = bytes.size)
    }

    @Benchmark
    fun packArrayUnsafe() {
        endian.packUnsafe(source = bytes, dest = ints, destOffset = 0, sourceIndexStart = 0, sourceIndexEnd = bytes.size)
    }

    @Benchmark
    fun packPartial() {
        endian.pack(-42, bytes, 0, sourceIndexStart = 1)
    }

    @Benchmark
    fun packPartialUnsafe() {
        endian.packUnsafe(-42, bytes, 0, sourceIndexStart = 1)
    }

    @Benchmark
    fun unpackArray() {
        endian.pack(source = ints, dest = bytes, destOffset = 0)
    }

    @Benchmark
    fun unpackArrayUnsafe() {
        endian.packUnsafe(source = ints, dest = bytes, destOffset = 0)
    }
}

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(iterations = ITERATIONS, time = TIME_WARMUP)
@Measurement(iterations = ITERATIONS, time = TIME_MEASURE)
open class EndianBigBenchmark: EndianBenchmarkBase(Endian.Big)

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(BenchmarkTimeUnit.NANOSECONDS)
@Warmup(iterations = ITERATIONS, time = TIME_WARMUP)
@Measurement(iterations = ITERATIONS, time = TIME_MEASURE)
open class EndianLittleBenchmark: EndianBenchmarkBase(Endian.Little)
