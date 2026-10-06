package com.pemmob.katalogbuku.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.pemmob.katalogbuku.data.model.BookDto
import com.pemmob.katalogbuku.data.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: BookRepository) : ViewModel() {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _searchInput = MutableStateFlow("")
    val searchInput: StateFlow<String> = _searchInput.asStateFlow()

    init {
        search("indonesia")
    }

    fun updateSearchInput(newInput: String) {
        _searchInput.value = newInput
    }

    fun search(query: String) {
        if (query.isBlank()) return
        
        _uiState.value = HomeUiState.Loading
        viewModelScope.launch {
            val result = repository.searchBooks(query)
            result.fold(
                onSuccess = { books ->
                    _uiState.value = HomeUiState.Success(query, books)
                },
                onFailure = {
                    _uiState.value = HomeUiState.Error(it.message ?: "Terjadi kesalahan")
                }
            )
        }
    }

    fun findBook(key: String): BookDto? {
        val currentState = _uiState.value
        return if (currentState is HomeUiState.Success) {
            currentState.books.find { it.key == key }
        } else {
            null
        }
    }
}

class HomeViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        HomeViewModel(repository) as T
}
