package com.example.room.dao

import androidx.room.*
import com.example.room.model.UserDTO

/**
 *  author : ly
 *  date : 2020/9/15 11:04
 *  description : 用户数据库操作
 */
@Dao
public interface UserDAO {
    /**
     * 查询所有用户
     */
    @Query("SELECT * FROM user")
    fun getAll(): List<UserDTO>

    /**
     *根据Id 查询到某一个用户
     */
    @Query("select *from user where id = (:uid)")
    fun loadAllById(uid: Int): UserDTO

    /**
     * 根据用户ID 数据查询到一批用户
     */
    @Query("select *from user where id in (:ids)")
    fun loadAllByIds(ids: IntArray): List<UserDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: UserDTO?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserDTO>)

    @Delete
    fun deleteUser(user: UserDTO?)

    /**
     * 返回name 的子集
     */
    @Query("SELECT name FROM user")
    fun loadFullName(): List<NameTuple>
}

data class NameTuple(
        @ColumnInfo(name = "name") var name: String?
)