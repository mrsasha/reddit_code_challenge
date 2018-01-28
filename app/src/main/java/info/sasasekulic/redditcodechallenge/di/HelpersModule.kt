package info.sasasekulic.redditcodechallenge.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import info.sasasekulic.redditcodechallenge.App
import javax.inject.Singleton

@Module
class HelpersModule {

    @Provides
    fun provideApplicationContext(app: App): Context = app.applicationContext
}