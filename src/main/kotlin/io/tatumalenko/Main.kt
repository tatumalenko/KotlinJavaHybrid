package io.tatumalenko

import io.tatumalenko.concurrency.ConcurrencyJava
import io.tatumalenko.concurrency.ConcurrencyKotlin
import io.tatumalenko.model.Download
import io.tatumalenko.model.Download.App
import io.tatumalenko.model.Person
import io.tatumalenko.patternmatching.PatternMatchingKotlin

sealed interface Downloadd
data class Appp(val name: String, val developer: Person) : Downloadd
data class Moviee(val title: String, val director: Person) : Downloadd

fun main() {
    val download: Download = App("Alice", Person("Alice", 18))
    println(PatternMatchingKotlin.run(download))

    val downloadd: Downloadd = Appp("Alice", Person("Alice", 18))
    println(PatternMatchingKotlin.run2(downloadd))

    ConcurrencyJava.run()
    ConcurrencyKotlin.run()
}
