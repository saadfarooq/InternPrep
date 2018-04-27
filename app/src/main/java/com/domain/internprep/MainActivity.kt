package com.domain.internprep

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.domain.internprep.databinding.ActivityMainBinding
import dagger.Module
import dagger.Provides
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var appDependency: AppLevelDependency
    @Inject lateinit var activityLevelDependency: ActivityLevelDependency

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as InternPrepApp).objectGraph
                .plus(ActivityModule(this))
                .inject(this)
        val binding = DataBindingUtil.setContentView(this, R.layout.activity_main) as ActivityMainBinding
        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}

@Module(
        injects = [(MainActivity::class)],
        addsTo = AppModule::class,
        library = true)
class ActivityModule(val activity: android.app.Activity) {
    @Provides
    fun providesActivityLevelDependecy() = ActivityLevelDependency(activity)
}

data class ActivityLevelDependency(val context: android.content.Context) {
    val activityLevelValue = "String provided by Activity"
}
