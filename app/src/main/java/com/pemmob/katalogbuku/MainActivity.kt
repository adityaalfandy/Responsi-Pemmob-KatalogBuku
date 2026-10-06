package com.pemmob.katalogbuku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pemmob.katalogbuku.data.remote.RetrofitClient
import com.pemmob.katalogbuku.data.repository.BookRepository
import com.pemmob.katalogbuku.ui.home.HomeScreen
import com.pemmob.katalogbuku.ui.home.HomeViewModel
import com.pemmob.katalogbuku.ui.home.HomeViewModelFactory
import com.pemmob.katalogbuku.ui.theme.KatalogBukuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val repository = BookRepository(RetrofitClient.api)
        
        setContent {
            KatalogBukuTheme {
                val viewModel: HomeViewModel = viewModel(
                    factory = HomeViewModelFactory(repository)
                )
                val navController = androidx.navigation.compose.rememberNavController()
                
                com.pemmob.katalogbuku.ui.navigation.AppNavHost(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}