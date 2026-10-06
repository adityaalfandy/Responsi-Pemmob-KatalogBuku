package com.pemmob.katalogbuku.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pemmob.katalogbuku.ui.detail.DetailScreen
import com.pemmob.katalogbuku.ui.home.HomeScreen
import com.pemmob.katalogbuku.ui.home.HomeViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME
    ) {
        composable(route = Routes.HOME) {
            val uiState by viewModel.uiState.collectAsState()
            val searchInput by viewModel.searchInput.collectAsState()

            HomeScreen(
                uiState = uiState,
                searchInput = searchInput,
                onSearchInputChange = viewModel::updateSearchInput,
                onSearchSubmit = viewModel::search,
                onBookClick = { bookKey ->
                    // Encode url karena key dari OpenLibrary mengandung '/' (mis. /works/OL...)
                    val encodedKey = Uri.encode(bookKey)
                    navController.navigate(Routes.createDetailRoute(encodedKey))
                }
            )
        }

        composable(
            route = Routes.DETAIL,
            arguments = listOf(
                navArgument("bookKey") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val encodedKey = backStackEntry.arguments?.getString("bookKey") ?: ""
            val bookKey = Uri.decode(encodedKey)
            
            // Ambil data buku dari viewmodel tanpa melakukan pemanggilan API baru
            val book = viewModel.findBook(bookKey)
            
            DetailScreen(
                book = book,
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
