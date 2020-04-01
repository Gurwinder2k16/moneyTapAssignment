package com.assignment.appstreetassignment.di.module.fragment

import com.assignment.appstreetassignment.module.fragments.DetailFragment
import com.assignment.appstreetassignment.module.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment(): DetailFragment
}