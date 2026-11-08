package com.dofkee.bookme.ui.theme

import com.dofkee.bookme.domain.model.Book

sealed interface BookUiState {
    data object Loading : BookUiState
    data class Success(val books: List<Book>) : BookUiState
    data class Error(val message: String) : BookUiState
}

