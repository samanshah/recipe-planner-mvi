package com.geekstudio.recipeplanner.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geekstudio.recipeplanner.data.local.entity.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

//ORDER BY timestamp DESC
    @Query(
        """
        SELECT *
        FROM search_history
        """
    )
    fun observeSearchHistory(): Flow<List<SearchHistoryEntity>>

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun insertSearchQuery(
        searchHistory: SearchHistoryEntity
    )

    @Query(
        """
        DELETE FROM search_history
        """
    )
    suspend fun clearHistory()
}