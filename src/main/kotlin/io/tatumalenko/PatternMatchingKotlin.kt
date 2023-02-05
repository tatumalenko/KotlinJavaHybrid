package io.tatumalenko

import io.tatumalenko.model.Download

object PatternMatchingKotlin {
    fun run(download: Download): String = when (download) {
        is Download.App -> {
            // val (name, user) = download <-- Destructuring declaration initializer of type Download must have a 'component1()' function
            when (download.developer.name) {
                "Alice" -> "Alice's app ${download.name}"
                else -> "Not Alice's app"
            }
        }
        is Download.Movie -> {
            when (download.director.name) {
                "Alice" -> "Alice's movie ${download.title}"
                else -> "Not Alice's movie"
            }
        }
        else -> "No exhaustive when if using Java Records"
    }

    fun run2(downloadd: Downloadd): String = when (downloadd) {
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
}
