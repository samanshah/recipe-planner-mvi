package com.geekstudio.recipeplanner.domain.repository

import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    fun observeHistory(): Flow<List<String>>

    suspend fun clearHistory()

    suspend fun deleteQuery(
        query: String
    )

}