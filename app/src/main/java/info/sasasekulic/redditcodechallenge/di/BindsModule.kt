package info.sasasekulic.redditcodechallenge.di

import dagger.Binds
import dagger.Module
import info.sasasekulic.redditcodechallenge.repositories.IPreferencesRepository
import info.sasasekulic.redditcodechallenge.repositories.IRedditDataRepository
import info.sasasekulic.redditcodechallenge.repositories.PreferencesRepository
import info.sasasekulic.redditcodechallenge.repositories.RedditDataRepository

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindRedditDataRepository(redditDataRepository: RedditDataRepository): IRedditDataRepository

    @Binds
    abstract fun bindPreferencesRepository(preferencesManager: PreferencesRepository): IPreferencesRepository
}