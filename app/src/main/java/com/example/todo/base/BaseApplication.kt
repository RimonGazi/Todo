package com.example.todo.base

import com.example.todo.analytics.CrashReportingTree
import com.example.todo.BuildConfig
import com.example.todo.R
import com.facebook.stetho.Stetho
import dagger.android.support.DaggerApplication
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber

abstract class BaseApplication : DaggerApplication() {
    private val DEFAULT_FONT_PATH = "fonts/Roboto-RobotoRegular.ttf"

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        initCalligraphy()
        initTimber()
    }

    private fun initCalligraphy() {
        val mCalligraphyConfig = CalligraphyConfig.Builder()
            // Todo: 07/25/2019 (GAZI RIMON) add default here
            // .setDefaultFontPath(DEFAULT_FONT_PATH)
            .setFontAttrId(R.attr.fontPath)
            .build()
        val mInterceptor = CalligraphyInterceptor(mCalligraphyConfig)
        val mViewPumpBuilder = ViewPump.builder()
            .addInterceptor(mInterceptor)
            .build()
        ViewPump.init(mViewPumpBuilder)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            val mdDebugTree = Timber.DebugTree()
            Timber.plant(mdDebugTree)
        } else {
            val mCrashReportingTree = CrashReportingTree()
            Timber.plant(mCrashReportingTree)
        }
    }
}
