package com.sarpertezel.artbooktest.repo

import androidx.lifecycle.LiveData
import com.sarpertezel.artbooktest.model.ImageResponse
import com.sarpertezel.artbooktest.roomdb.Art
import com.sarpertezel.artbooktest.util.Resource

interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art : Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String ) : Resource<ImageResponse>
}