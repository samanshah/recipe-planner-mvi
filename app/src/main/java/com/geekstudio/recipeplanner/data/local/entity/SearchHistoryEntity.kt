package com.geekstudio.recipeplanner.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "search_history")
data class SearchHistoryEntity(

    @PrimaryKey
    val queryStr: String,

    val timestamp: Long = System.currentTimeMillis()

)