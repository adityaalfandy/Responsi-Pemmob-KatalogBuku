package com.pemmob.katalogbuku.data.repository

import com.pemmob.katalogbuku.data.model.BookDto
import com.pemmob.katalogbuku.data.remote.OpenLibraryApi
import kotlinx.coroutines.CancellationException

class BookRepository(private val api: OpenLibraryApi) {
    suspend fun searchBooks(query: String, limit: Int = 20): Result<List<BookDto>> {
        return try {
            val response = api.searchBooks(query, limit)
            val filteredBooks = response.docs.filter { !it.key.isNullOrBlank() }
            Result.success(filteredBooks)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
