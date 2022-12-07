package me.amitshekhar.mvvm.di.component

import android.content.Context
import dagger.Component
import me.amitshekhar.mvvm.MVVMApplication
import me.amitshekhar.mvvm.data.api.NetworkService
import me.amitshekhar.mvvm.data.repository.TopHeadlineRepository
import me.amitshekhar.mvvm.di.ApplicationContext
import me.amitshekhar.mvvm.di.module.ApplicationModule
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: MVVMApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

}