package com.gzeinnumer.myandroidtemplate3kt.dagger

import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthScope
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainScope
import com.gzeinnumer.myandroidtemplate3kt.ui.auth.AuthActivity
import com.gzeinnumer.myandroidtemplate3kt.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    @MainScope
    @ContributesAndroidInjector
    abstract fun constributeMainActivity(): MainActivity

    @AuthScope
    @ContributesAndroidInjector
    abstract fun constributeAuthActivity(): AuthActivity
}