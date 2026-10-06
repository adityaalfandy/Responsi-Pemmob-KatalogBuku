package com.pemmob.katalogbuku.ui.home

import com.pemmob.katalogbuku.data.model.BookDto

sealed interface HomeUiState {
    data object Loading : HomeUiState
    data class Success(val query: String, val books: List<BookDto>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}
