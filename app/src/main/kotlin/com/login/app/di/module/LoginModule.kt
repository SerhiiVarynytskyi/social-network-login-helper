package com.login.app.di.module

import android.content.Context
import com.login.helper.LoginHelper
import com.login.helper.impl.ownserver.LoginDataSource
import com.login.helper.impl.ownserver.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LoginModule {

    @Provides
    @Singleton
    fun provideLoginHelper(context: Context): LoginHelper = LoginHelper(context)


    @Provides
    fun provideLoginDataSource(): LoginDataSource =
        LoginDataSource()

    @Provides
    fun provideLoginRepository(dataSource: LoginDataSource): LoginRepository =
        LoginRepository(dataSource)
}