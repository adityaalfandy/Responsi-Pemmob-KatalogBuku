package com.pemmob.katalogbuku.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pemmob.katalogbuku.R
import com.pemmob.katalogbuku.data.model.BookDto
import com.pemmob.katalogbuku.ui.components.BookItem
import com.pemmob.katalogbuku.ui.components.BookSearchBar
import com.pemmob.katalogbuku.ui.components.EmptyView
import com.pemmob.katalogbuku.ui.components.ErrorView
import com.pemmob.katalogbuku.ui.components.LoadingView
import com.pemmob.katalogbuku.ui.theme.KatalogBukuTheme
import com.pemmob.katalogbuku.ui.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    searchInput: String,
    onSearchInputChange: (String) -> Unit,
    onSearchSubmit: (String) -> Unit,
    onBookClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        text = stringResource(R.string.app_title_home),
                        style = MaterialTheme.typography.titleLarge
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            BookSearchBar(
                query = searchInput,
                onQueryChange = onSearchInputChange,
                onSearch = { onSearchSubmit(searchInput) },
                modifier = Modifier.padding(horizontal = Spacing.lg, vertical = Spacing.sm)
            )
            
            when (uiState) {
                is HomeUiState.Loading -> {
                    LoadingView(modifier = Modifier.weight(1f))
                }
                is HomeUiState.Success -> {
                    if (uiState.books.isEmpty()) {
                        EmptyView(
                            query = uiState.query,
                            modifier = Modifier.weight(1f)
                        )
                    } else {
                        LazyColumn(
                            modifier = Modifier.weight(1f),
                            contentPadding = PaddingValues(
                                start = Spacing.lg,
                                end = Spacing.lg,
                                bottom = Spacing.lg
                            ),
                            verticalArrangement = Arrangement.spacedBy(Spacing.sm)
                        ) {
                            items(
                                items = uiState.books,
                                key = { it.key ?: it.hashCode().toString() }
                            ) { book ->
                                BookItem(
                                    book = book,
                                    onClick = { book.key?.let { onBookClick(it) } }
                                )
                            }
                        }
                    }
                }
                is HomeUiState.Error -> {
                    ErrorView(
                        message = uiState.message,
                        onRetry = { onSearchSubmit(searchInput) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KatalogBukuTheme {
        HomeScreen(
            uiState = HomeUiState.Success(
                query = "Android",
                books = listOf(
                    BookDto(key = "1", title = "Compose", authorName = listOf("Google"))
                )
            ),
            searchInput = "",
            onSearchInputChange = {},
            onSearchSubmit = {},
            onBookClick = {}
        )
    }
}

@Preview(showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenDarkPreview() {
    KatalogBukuTheme {
        HomeScreen(
            uiState = HomeUiState.Success(
                query = "Android",
                books = listOf(
                    BookDto(key = "1", title = "Compose", authorName = listOf("Google"))
                )
            ),
            searchInput = "",
            onSearchInputChange = {},
            onSearchSubmit = {},
            onBookClick = {}
        )
    }
}
