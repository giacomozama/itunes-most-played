package me.zama.itunes_most_played.data.source.remote

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SongServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(SongService.BaseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideSongService(retrofit: Retrofit): SongService = retrofit.create(SongService::class.java)
}
