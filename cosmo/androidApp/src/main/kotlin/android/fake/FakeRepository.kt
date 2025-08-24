// FakeRepository.kt
package com.yourcompany.cosmo.android.fake

data class Star(val id: String, val name: String, val x: Float, val y: Float, val genre: String)

fun fakeStarsForGalaxy(galaxyId: String) = List(100) { i ->
    val fx = ((i * 37) % 100) / 100f - 0.5f
    val fy = ((i * 91) % 100) / 100f - 0.5f
    Star("$galaxyId-$i", "Star $i", fx, fy, listOf("Book","TED","Film","Podcast")[i % 4])
}
