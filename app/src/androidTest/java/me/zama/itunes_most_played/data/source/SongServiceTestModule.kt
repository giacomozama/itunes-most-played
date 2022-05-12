package me.zama.itunes_most_played.data.source

import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import me.zama.itunes_most_played.data.source.remote.SongService
import me.zama.itunes_most_played.data.source.remote.SongServiceModule

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [SongServiceModule::class])
class SongServiceTestModule {

    @Provides
    fun provideSongService(): SongService = SongServiceTestImpl()
}
