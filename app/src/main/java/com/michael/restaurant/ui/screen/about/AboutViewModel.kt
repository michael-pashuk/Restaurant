package com.michael.restaurant.ui.screen.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.michael.restaurant.data.repository.AboutRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
    private val repository: AboutRepository
) : ViewModel() {

    private val _aboutState = MutableStateFlow<AboutState>(AboutState.Loading)
    val aboutState: StateFlow<AboutState> = _aboutState.asStateFlow()

    init {
        viewModelScope.launch {
            _aboutState.value = AboutState.Loading

            val newState = repository.getAboutByLanguageCode("en")

            if (newState == null) {
                _aboutState.value = AboutState.Error("Localization not found")
            } else {
                _aboutState.value = AboutState.Success(newState)
            }
        }
    }

}