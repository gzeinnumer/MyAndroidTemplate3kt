package com.gzeinnumer.myandroidtemplate3kt.dagger.auth

import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelKey
import com.gzeinnumer.myandroidtemplate3kt.ui.auth.AuthVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelsModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthVM::class)
    abstract fun bindAuthViewModel(viewModel: AuthVM): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainVM::class)
//    abstract fun bindMainViewModel(viewModel: MainVM): ViewModel
    //untuk menggunakan 2 VM atau labih VM, bisa tambahkan disini, VM yang dipakia untuk mengganti Bundle
}