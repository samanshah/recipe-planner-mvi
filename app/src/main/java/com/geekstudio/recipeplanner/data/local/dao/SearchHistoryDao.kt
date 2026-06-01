package com.geekstudio.recipeplanner.data.local.dao

import androidx.room.Insert
import androidx.room.Query
import com.geekstudio.recipeplanner.data.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchHistoryDao {
    @Insert
    suspend fun insertSearch(
        search: SearchHistoryEntity
    )

    @Query(
        """
SELECT * FROM search_history
ORDER BY id DESC
LIMIT 10
"""
    )
    fun observeHistory(): Flow<List<SearchHistoryEntity>>
}