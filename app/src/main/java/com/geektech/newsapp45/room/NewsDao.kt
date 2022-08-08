package com.geektech.newsapp45.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.geektech.newsapp45.models.News

@Dao
interface NewsDao {

    @Query("SELECT * FROM news order by createAt desc")
    fun getAll(): List<News>

    @Query("SELECT * FROM news ORDER BY createAt DESC")
    fun getAll2(): List<News>

    @Insert
    fun insert (news: News)

    @Query("SELECT * FROM news WHERE title LIKE '%' ||:search|| '%' " )
    fun getSearch(search: String): List <News>

}