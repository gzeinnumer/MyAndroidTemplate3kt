package com.gzeinnumer.myandroidtemplate3kt.dagger.main

import androidx.lifecycle.ViewModel
import com.gzeinnumer.myandroidtemplate3kt.dagger.ViewModelKey
import com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post.PostVM
import com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile.ProfileVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileVM::class)
    abstract fun bidProfileViewModel(profileVM: ProfileVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostVM::class)
    abstract fun bidPostViewModel(postVM: PostVM): ViewModel
}