package com.eve.mypagination.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.eve.mypagination.data.CharacterRepository
import com.eve.mypagination.network.CharacterModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    characterRepository: CharacterRepository,
) : ViewModel() {

    val character: LiveData<PagingData<CharacterModel>> =
        characterRepository.getAllCharacter().cachedIn(viewModelScope)
}