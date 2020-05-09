package com.gzeinnumer.myandroidtemplate3kt.dagger.main

import com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.post.PostFragment
import com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.postLocal.PostLocalFragment
import com.gzeinnumer.myandroidtemplate3kt.ui.main.ui.profile.ProfileFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun constributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun constributePostFragment(): PostFragment

    @ContributesAndroidInjector
    abstract fun constributePostLocalFragment(): PostLocalFragment
}