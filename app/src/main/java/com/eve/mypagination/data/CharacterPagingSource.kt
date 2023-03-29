package com.eve.mypagination.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.eve.mypagination.network.ApiService
import com.eve.mypagination.network.CharacterModel

class CharacterPagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.plus(1) ?: page?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val page = params.key ?: INITIAL_PAGE_INDEX
            val response = apiService.getAllCharacter(page, "", "", "")

            if (response.code() == 404) throw Exception(response.code().toString())

            val responseData = response.body()?.results ?: emptyList()
            LoadResult.Page(
                data = responseData,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (responseData.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
}