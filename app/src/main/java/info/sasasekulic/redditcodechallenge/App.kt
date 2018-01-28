package info.sasasekulic.redditcodechallenge

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import info.sasasekulic.redditcodechallenge.di.DaggerAppComponent
import javax.inject.Inject

open class App : Application(), HasActivityInjector {

    @Inject
    lateinit var mAndroidDispatchingInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = mAndroidDispatchingInjector
}