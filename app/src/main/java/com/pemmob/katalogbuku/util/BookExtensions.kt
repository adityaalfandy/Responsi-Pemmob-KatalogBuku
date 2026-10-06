package com.pemmob.katalogbuku.util

import java.util.Locale

fun List<String>?.toAuthorText(): String {
    return if (this.isNullOrEmpty()) "Penulis tidak diketahui" else this.joinToString(", ")
}

fun Int?.toYearText(): String {
    return this?.toString() ?: "Tahun tidak tersedia"
}

fun Int?.toEditionText(): String {
    return if (this != null) "$this edisi" else "Tidak tersedia"
}

fun List<String>?.toLanguageText(): String {
    if (this.isNullOrEmpty()) return "Tidak tersedia"
    
    val manualMap = mapOf(
        "ind" to "Indonesia",
        "eng" to "Inggris",
        "ger" to "Jerman",
        "fre" to "Prancis",
        "spa" to "Spanyol",
        "jav" to "Jawa"
    )
    
    val mapped = this.map { code ->
        manualMap[code] ?: try {
            val locale = Locale(code)
            val display = locale.getDisplayLanguage(Locale.forLanguageTag("id"))
            if (display.equals(code, ignoreCase = true)) code.uppercase(Locale.ROOT) else display
        } catch (e: Exception) {
            code.uppercase(Locale.ROOT)
        }
    }
    
    return if (mapped.size > 5) {
        mapped.take(5).joinToString(", ") + ", +${mapped.size - 5} lainnya"
    } else {
        mapped.joinToString(", ")
    }
}
