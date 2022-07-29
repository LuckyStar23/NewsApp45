package com.geektech.newsapp45.room

import androidx.room.Dao
import androidx.room.Insert
import com.geektech.newsapp45.models.News

@Dao
interface NewsDao {

    @Insert
    fun insert (news: News)
}