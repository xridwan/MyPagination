package com.eve.mypagination.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.eve.mypagination.network.ApiService
import com.eve.mypagination.network.CharacterModel
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : CharacterRepository {

    override fun getAllCharacter(): LiveData<PagingData<CharacterModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                CharacterPagingSource(apiService)
            }
        ).liveData
    }
}