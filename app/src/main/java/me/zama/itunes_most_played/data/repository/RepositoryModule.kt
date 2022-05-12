package me.zama.itunes_most_played.data.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindSongsRepository(songsRepositoryImpl: SongsRepositoryImpl): SongsRepository
}