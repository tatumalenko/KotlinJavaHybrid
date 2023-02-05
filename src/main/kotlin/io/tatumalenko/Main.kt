package io.tatumalenko

import io.tatumalenko.model.Download
import io.tatumalenko.model.Download.App
import io.tatumalenko.model.Download.Movie
import io.tatumalenko.model.Person

sealed interface Downloadd
data class Appp(val name: String, val developer: Person) : Downloadd
data class Moviee(val title: String, val director: Person) : Downloadd

fun main() {
    val download: Download = App("Alice", Person("Alice", 18))
    println(patternMatching(download))

    val downloadd: Downloadd = Appp("Alice", Person("Alice", 18))
    println(patternMatching(downloadd))

    ConcurrencyJava.run()
    ConcurrencyKotlin.run()
}

private fun patternMatching(download: Download): String = when (download) {
    is App -> {
        // val (name, user) = download <-- Destructuring declaration initializer of type Download must have a 'component1()' function
        when (download.developer.name) {
            "Alice" -> "Alice's app ${download.name}"
            else -> "Not Alice's app"
        }
    }
    is Movie -> {
        when (download.director.name) {
            "Alice" -> "Alice's movie ${download.title}"
            else -> "Not Alice's movie"
        }
    }
    else -> "No exhaustive when if using Java Records"
}

private fun patternMatching(downloadd: Downloadd): String = when (downloadd) {
    is Appp -> {
        val (appName, developer) = downloadd
        when (developer.name) {
            "Alice" -> "Alice's app $appName"
            else -> "Not Alice's app"
        }
    }
    is Moviee -> {
        val (movieName, director) = downloadd
        when (director.name) {
            "Alice" -> "Alice's movie $movieName"
            else -> "Not Alice's movie"
        }
    }
}
