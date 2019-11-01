package com.login.app.di.component


import com.login.app.App
import com.login.app.di.module.AppModule
import com.login.app.di.module.LoginModule
import com.login.app.di.module.MainModule
import com.login.app.di.viewmodel.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelModule::class,
        MainModule::class,

        LoginModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()

}