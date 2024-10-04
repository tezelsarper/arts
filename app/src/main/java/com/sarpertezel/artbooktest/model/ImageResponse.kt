package com.sarpertezel.artbooktest.model

data class ImageResponse(
    val hits : List<ImageResult>,
    val total : Int,
    val totalHist : Int

)
