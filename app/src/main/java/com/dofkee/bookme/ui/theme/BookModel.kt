package com.dofkee.bookme.ui.theme

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
        // подписка на вьюмодел
        viewModelScope.launch {
            repository.observeAll()
                .catch { e ->
                    _state.value = BookUiState.Error(e.message ?: "Неизвестная ошибка")
                }
                .collect { books ->
                    _state.value = BookUiState.Success(books)
                }
        }
    }
    fun onLikeClicked(book: Book) {
        viewModelScope.launch {
            // из репо
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