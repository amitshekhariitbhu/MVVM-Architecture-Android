package me.amitshekhar.mvvm.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.amitshekhar.mvvm.MVVMApplication
import me.amitshekhar.mvvm.data.api.NetworkService
import me.amitshekhar.mvvm.di.ApplicationContext
import me.amitshekhar.mvvm.di.BaseUrl
import me.amitshekhar.mvvm.ui.topheadline.TopHeadlineActivity
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideAppCompatActivity(): AppCompatActivity {
        // You can provide an instance of your application's main activity here.
        // If your app has multiple activities, you may need to adapt this based on your use case.
        return TopHeadlineActivity()
    }

    @ApplicationContext
    @Provides
    fun provideContext(application: MVVMApplication): Context {
        return application
    }

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://newsapi.org/v2/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

}