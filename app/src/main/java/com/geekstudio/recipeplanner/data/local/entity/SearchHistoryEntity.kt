package com.geekstudio.recipeplanner.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Timestamp

@Entity(tableName = "search_history")
data class SearchHistoryEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val queryStr: String,

    val timestamp: String

)