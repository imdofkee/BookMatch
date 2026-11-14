package com.dofkee.bookme.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dofkee.bookme.domain.model.Book
import com.dofkee.bookme.domain.model.CollectionType
import com.dofkee.bookme.domain.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val repository: BookRepository
) : ViewModel() {

    private val _state = MutableStateFlow<BookUiState>(BookUiState.Loading)
    val state: StateFlow<BookUiState> = _state.asStateFlow()

    init {
        // подписка на локальную БД
        viewModelScope.launch {
            repository.observeAll()
                .catch { e ->
                    Log.wtf("BOOKME_DIAG", "DB error: ${e.message}", e)
                    _state.value = BookUiState.Error("DB: ${e.message}")
                }
                .collect { books ->
                    Log.wtf("BOOKME_DIAG", "Room emitted ${books.size} books")
                    if (_state.value !is BookUiState.Error || books.isNotEmpty()) {
                        _state.value = BookUiState.Success(books)
                    }
                }
        }
        viewModelScope.launch {
            Log.wtf("BOOKME_DIAG", "→ API call starting, query='book'")
            try {
                val books = repository.getAllBooksFromAPI(search = "book")
                Log.wtf("BOOKME_DIAG", "✓ API returned ${books.size} books")
                if (books.isEmpty()) {
                    _state.value = BookUiState.Error("API вернул 0 книг (пустой ответ)")
                }
            } catch (e: Exception) {
                Log.wtf(
                    "BOOKME_DIAG",
                    "✗ API failed: ${e::class.simpleName}: ${e.message}",
                    e
                )
                _state.value = BookUiState.Error(
                    "API: ${e::class.simpleName}: ${e.message ?: " . "}"
                )
            }
        }
    }

    fun onLikeClicked(book: Book) {
        viewModelScope.launch {
            repository.addIntoCollection(book.id, CollectionType.liked)
        }
    }

    fun onDislikeClicked(book: Book) {
        viewModelScope.launch {
            repository.addIntoCollection(book.id, CollectionType.disliked)
        }
    }

    fun onLaterClicked(book: Book) {
        viewModelScope.launch {
            repository.addIntoCollection(book.id, CollectionType.later)
        }
    }
}