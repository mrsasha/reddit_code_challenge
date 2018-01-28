package info.sasasekulic.redditcodechallenge.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import info.sasasekulic.redditcodechallenge.screens.MainActivity

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}