package com.pemmob.katalogbuku.util

import org.junit.Assert.assertEquals
import org.junit.Test

class BookExtensionsTest {

    @Test
    fun testToAuthorText() {
        val authors = listOf("A", "B")
        assertEquals("A, B", authors.toAuthorText())
        val emptyAuthors: List<String>? = null
        assertEquals("Penulis tidak diketahui", emptyAuthors.toAuthorText())
    }

    @Test
    fun testToYearText() {
        val year = 1980
        assertEquals("1980", year.toYearText())
        val nullYear: Int? = null
        assertEquals("Tahun tidak tersedia", nullYear.toYearText())
    }

    @Test
    fun testToEditionText() {
        val edition = 45
        assertEquals("45 edisi", edition.toEditionText())
        val nullEdition: Int? = null
        assertEquals("Tidak tersedia", nullEdition.toEditionText())
    }

    @Test
    fun testToLanguageText() {
        val languages = listOf("ind", "eng")
        assertEquals("Indonesia, Inggris", languages.toLanguageText())
        
        val manyLanguages = listOf("ind", "eng", "ger", "fre", "spa", "jav", "kor")
        assertEquals("Indonesia, Inggris, Jerman, Prancis, Spanyol, +2 lainnya", manyLanguages.toLanguageText())

        val unknownLanguage = listOf("xyz")
        assertEquals("XYZ", unknownLanguage.toLanguageText())
        
        val nullLanguages: List<String>? = null
        assertEquals("Tidak tersedia", nullLanguages.toLanguageText())
    }
}
