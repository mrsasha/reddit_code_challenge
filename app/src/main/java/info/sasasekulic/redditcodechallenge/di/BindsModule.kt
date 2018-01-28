package info.sasasekulic.redditcodechallenge.di

import dagger.Binds
import dagger.Module
import info.sasasekulic.redditcodechallenge.repositories.IPreferencesRepository
import info.sasasekulic.redditcodechallenge.repositories.PreferencesRepository

@Module
abstract class BindsModule {

    @Binds
    abstract fun bindPreferencesRepository(preferencesManager: PreferencesRepository): IPreferencesRepository
}