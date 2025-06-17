package com.michael.restaurant.ui.screen.menu.delicious

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.restaurant.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeliciousViewModel
@Inject constructor(
    private val repository: MenuRepository
) : ViewModel() {
    private val _deliciousState = MutableStateFlow<DeliciousState>(DeliciousState.Loading)
    val deliciousState = _deliciousState.asStateFlow()

    init {
        _deliciousState.value = DeliciousState.Loading

        viewModelScope.launch {
            val categories = repository.getAllCategory()
            val dishes = repository.getOnMainInQuantity(21)

            if (dishes.isEmpty()) {
                _deliciousState.value = DeliciousState.Error(null)
            } else {
                _deliciousState.value = DeliciousState.Success(
                    category = categories,
                    dishes = dishes
                )
            }
        }
    }

    fun selectCategory(id: Int) {
        if (_deliciousState.value is DeliciousState.Success) {
            val oldState = _deliciousState.value as DeliciousState.Success

            viewModelScope.launch {
                if (oldState.selectedCategoryId == id) {
                    val dishes = repository.getOnMainInQuantity(21)
                    _deliciousState.value = oldState.copy(
                        selectedCategoryId = null,
                        dishes = dishes
                    )
                } else {
                    val filteredDishes = repository.getDishesByCategoryOnMain(id)
                    _deliciousState.value = oldState.copy(
                        selectedCategoryId = id,
                        dishes = filteredDishes
                    )
                }
            }
        }
    }

}