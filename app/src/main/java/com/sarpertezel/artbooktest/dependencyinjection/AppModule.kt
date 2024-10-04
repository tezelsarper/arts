package com.sarpertezel.artbooktest.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sarpertezel.artbooktest.R
import com.sarpertezel.artbooktest.api.RetrofitAPI
import com.sarpertezel.artbooktest.repo.ArtRepository
import com.sarpertezel.artbooktest.repo.ArtRepositoryInterface
import com.sarpertezel.artbooktest.roomdb.ArtDao
import com.sarpertezel.artbooktest.roomdb.ArtDatabase
import com.sarpertezel.artbooktest.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,ArtDatabase::class.java,"ArtBookDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database : ArtDatabase) = database.artDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI
    {
        return Retrofit.Builder().
        addConverterFactory(GsonConverterFactory.create()).
        baseUrl(BASE_URL).
        build().
        create(RetrofitAPI::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(dao : ArtDao , api : RetrofitAPI) = ArtRepository(dao,api) as ArtRepositoryInterface



    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground)
    )

}