package com.example.multitasked.ui.boards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.multitasked.data.model.Board
import com.example.multitasked.data.model.BoardType
import com.example.multitasked.data.repository.BoardRepository
import com.example.multitasked.ui.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class BoardSortOption {
    NAME_ASC,
    NAME_DESC,
    TASK_COUNT_ASC,
    TASK_COUNT_DESC
}

data class BoardsUiState(
    val isLoading: Boolean = false,
    val allBoards: List<Board> = emptyList(),
    val searchQuery: String = "",
    val sortOption: BoardSortOption = BoardSortOption.NAME_ASC,
    val showCelebration: Boolean = true,
    val errorMessage: String? = null
) {
    val filteredBoards: List<Board>
        get() {
            val filtered = if (searchQuery.isBlank()) {
                allBoards
            } else {
                allBoards.filter { it.name.contains(searchQuery, ignoreCase = true) }
            }

            return when (sortOption) {
                BoardSortOption.NAME_ASC -> filtered.sortedBy { it.name.lowercase() }
                BoardSortOption.NAME_DESC -> filtered.sortedByDescending { it.name.lowercase() }
                BoardSortOption.TASK_COUNT_ASC -> filtered.sortedBy { it.taskCount }
                BoardSortOption.TASK_COUNT_DESC -> filtered.sortedByDescending { it.taskCount }
            }
        }
}

@HiltViewModel
class BoardsViewModel @Inject constructor(
    private val boardRepository: BoardRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    val uiState: StateFlow<BoardsUiState> = combine(
        boardRepository.getBoards(),
        settingsRepository.showCelebration
    ) { boards, showCelebration ->
        BoardsUiState(allBoards = boards, showCelebration = showCelebration)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BoardsUiState(isLoading = true))

    fun createBoard(name: String, description: String, type: BoardType) {
        viewModelScope.launch {
            try {
                boardRepository.createBoard(name, description, type)
            } catch (e: Exception) {
                // TODO: Handle error
            }
        }
    }

    fun updateBoard(boardId: String, name: String, description: String) {
        viewModelScope.launch {
            try {
                boardRepository.updateBoard(boardId, name, description)
            } catch (e: Exception) {
                // TODO: Handle error
            }
        }
    }

    fun joinBoard(boardId: String) {
        viewModelScope.launch {
            try {
                boardRepository.joinBoard(boardId)
            } catch (e: Exception) {
                // TODO: Handle error
            }
        }
    }

    fun deleteBoard(boardId: String) {
        viewModelScope.launch {
            try {
                boardRepository.deleteBoard(boardId)
            } catch (e: Exception) {
                // TODO: Handle error
            }
        }
    }

    fun setSearchQuery(query: String) {
        // This will be handled by the UI state combination
    }

    fun setSortOption(option: BoardSortOption) {
        // This will be handled by the UI state combination
    }

    fun setShowCelebration(show: Boolean) {
        viewModelScope.launch {
            settingsRepository.setShowCelebration(show)
        }
    }

    fun clearError() {
        // This will be handled by the UI state combination
    }
}
