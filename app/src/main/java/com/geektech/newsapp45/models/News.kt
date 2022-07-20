package com.geektech.newsapp45.models

import java.io.Serializable


data class News(
    val title: String,
    val createAt: Long
): Serializable
