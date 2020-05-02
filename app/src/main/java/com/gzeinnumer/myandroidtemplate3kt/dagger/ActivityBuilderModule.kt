package com.gzeinnumer.myandroidtemplate3kt.dagger

import com.gzeinnumer.myandroidtemplate3kt.base.BaseActivity
import com.gzeinnumer.myandroidtemplate3kt.base.BaseFragment
import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthModule
import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthScope
import com.gzeinnumer.myandroidtemplate3kt.dagger.auth.AuthViewModelsModule
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainFragmentBuilderModule
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainModule
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainScope
import com.gzeinnumer.myandroidtemplate3kt.dagger.main.MainViewModelsModule
import com.gzeinnumer.myandroidtemplate3kt.ui.auth.AuthActivity
import com.gzeinnumer.myandroidtemplate3kt.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule{

    @ContributesAndroidInjector
    abstract fun constributedBaseFragment(): BaseFragment

    @ContributesAndroidInjector
    abstract fun constributeBaseActivity(): BaseActivity

    @AuthScope
    @ContributesAndroidInjector(
        modules = [
            AuthViewModelsModule::class,
            AuthModule::class
        ]
    )
    abstract fun constributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(
        modules = [
            MainViewModelsModule::class,
            MainModule::class,
            MainFragmentBuilderModule::class
        ]
    )
    abstract fun constributeMainActivity(): MainActivity
}