package com.eve.mypagination.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.eve.mypagination.network.CharacterModel

interface CharacterRepository {
    fun getAllCharacter(): LiveData<PagingData<CharacterModel>>
}