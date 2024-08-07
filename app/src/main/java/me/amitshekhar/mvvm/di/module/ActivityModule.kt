package me.amitshekhar.mvvm.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.amitshekhar.mvvm.data.repository.TopHeadlineRepository
import me.amitshekhar.mvvm.di.ActivityContext
import me.amitshekhar.mvvm.ui.base.ViewModelProviderFactory
import me.amitshekhar.mvvm.ui.topheadline.TopHeadlineAdapter
import me.amitshekhar.mvvm.ui.topheadline.TopHeadlineViewModel

@Module
@InstallIn(SingletonComponent::class)
class ActivityModule {

    @ActivityContext
    @Provides
    fun provideContext(activity: AppCompatActivity): Context {
        return activity
    }

    /*@Provides
    fun provideTopHeadlineViewModel(
        activity: AppCompatActivity,
        topHeadlineRepository: TopHeadlineRepository
    ): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }*/

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

}