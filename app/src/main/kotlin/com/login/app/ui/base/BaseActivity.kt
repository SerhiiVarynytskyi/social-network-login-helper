package com.login.app.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<VModel : ViewModel> : DaggerAppCompatActivity() {

    abstract fun buildViewModel(): VModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    val viewModel: VModel by lazy { buildViewModel() }

}