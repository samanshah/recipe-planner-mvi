package com.geekstudio.recipeplanner.data.repository

import com.geekstudio.recipeplanner.data.local.dao.SearchHistoryDao
import com.geekstudio.recipeplanner.domain.repository.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SearchHistoryRepositoryImpl @Inject constructor(
    private val dao: SearchHistoryDao
) : SearchHistoryRepository {

    override fun observeHistory(): Flow<List<String>> {

        return dao.observeSearchHistory()
            .map { items ->
                items.map { it.query }
            }

    }

    override suspend fun clearHistory() {
        dao.clearHistory()
    }

    override suspend fun deleteQuery(
        query: String
    ) {
        dao.deleteQuery(query)
    }

}