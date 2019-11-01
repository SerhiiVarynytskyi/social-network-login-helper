package com.login.app.di.module

import com.login.app.ui.login.LoginActivity
import com.login.app.ui.profile.ProfileActivity
import com.login.app.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [])
internal abstract class MainModule {

    @ContributesAndroidInjector
    internal abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun profileActivity(): ProfileActivity

}