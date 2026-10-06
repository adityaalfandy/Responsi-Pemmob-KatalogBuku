package com.pemmob.katalogbuku.ui.navigation

object Routes {
    const val HOME = "home"
    const val DETAIL = "detail/{bookKey}"
    
    fun createDetailRoute(bookKey: String): String {
        return "detail/$bookKey"
    }
}
