package info.sasasekulic.redditcodechallenge.di

import android.content.Context
import com.github.salomonbrys.kotson.registerTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import info.sasasekulic.redditcodechallenge.App
import info.sasasekulic.redditcodechallenge.api.childDeserializer
import javax.inject.Singleton

@Module
class HelpersModule {

    @Provides
    fun provideApplicationContext(app: App): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder()
            .registerTypeAdapter (childDeserializer)
            .create()
}