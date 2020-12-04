package com.example.room.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 *  author : ly
 *  date : 2020/9/15 10:51
 *  description : 用户信息表
 */
@Entity(tableName = "user")
data class UserDTO(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Long, //用户ID
        @ColumnInfo(name = "name")
        var name: String?,//用户姓名
        @ColumnInfo(name = "address")
        var address: String?
)