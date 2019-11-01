package com.login.app.di.module

import android.content.Context
import com.login.app.App

import dagger.Module
import dagger.Provides

@Module()
class AppModule {

    @Provides
    fun providesContext(application: App): Context {
        return application.applicationContext
    }

}