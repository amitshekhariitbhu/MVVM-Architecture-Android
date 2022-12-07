package me.amitshekhar.mvvm

import android.app.Application
import me.amitshekhar.mvvm.di.component.ApplicationComponent
import me.amitshekhar.mvvm.di.component.DaggerApplicationComponent
import me.amitshekhar.mvvm.di.module.ApplicationModule

class MVVMApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        injectDependencies()
    }

    private fun injectDependencies() {
        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
        applicationComponent.inject(this)
    }

}