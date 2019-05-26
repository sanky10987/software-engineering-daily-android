package com.koalatea.sedaily.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface DownloadDao {
    @get:Query("SELECT download.filename AS filename, episode.title AS title, episode.featuredImage as featuredImage, download.postId as postId "
            + "FROM download, episode "
            + "WHERE download.postId = episode._id")
    val allDownloadsWithEpisodes: List<DownloadEpisode>

    @get:Query("SELECT * FROM download")
    val all: List<Download>

    @Query("SELECT * FROM download WHERE postId = :id LIMIT 1")
    fun findById(id: String): Download

    @Insert(onConflict = REPLACE)
    fun inserAll(vararg downloads: Download)

    @Delete
    fun delete(download: Download)

    data class DownloadEpisode(
            val postId: String,
            val filename: String,
            val title: String,
            val featuredImage: String?
    )
}