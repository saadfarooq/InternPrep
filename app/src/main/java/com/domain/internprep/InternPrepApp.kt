package com.domain.internprep

import android.app.Application
import dagger.Module
import dagger.ObjectGraph
import dagger.Provides

class InternPrepApp : Application() {
    lateinit var objectGraph: ObjectGraph

    override fun onCreate() {
        super.onCreate()
        objectGraph = ObjectGraph.create(AppModule(this))
    }


}

@Module
class AppModule(private val app: InternPrepApp) {
    @Provides
    fun providesAppLevelDependecy() = AppLevelDependency(app)
}

data class AppLevelDependency(val context: android.content.Context) {
    val appLevelString = "String provided by app"
}