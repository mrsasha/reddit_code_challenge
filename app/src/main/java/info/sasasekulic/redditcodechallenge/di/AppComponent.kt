package info.sasasekulic.redditcodechallenge.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import info.sasasekulic.redditcodechallenge.App
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        ActivityModule::class,
        AndroidSupportInjectionModule::class,
        BindsModule::class,
        NetworkModule::class,
        HelpersModule::class))
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }
}