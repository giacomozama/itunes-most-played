package me.zama.itunes_most_played.ui.coil

import coil.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [ImageLoaderModule::class])
class ImageLoaderTestModule {

    @Provides
    fun provideImageLoader(): ImageLoader = FakeImageLoader()
}