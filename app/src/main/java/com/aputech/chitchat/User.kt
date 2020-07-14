package com.aputech.chitchat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val uid: String,
    @ColumnInfo(name = "dark_mode") val darkMode: Int,
    @ColumnInfo(name = "Info") val Info: String?
)