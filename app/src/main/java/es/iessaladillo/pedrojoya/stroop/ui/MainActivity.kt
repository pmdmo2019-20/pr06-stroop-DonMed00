package es.iessaladillo.pedrojoya.stroop.ui


import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener
import es.iessaladillo.pedrojoya.stroop.extensions.hideSoftKeyboard
import kotlinx.android.synthetic.main.player_edit_fragment.*


class MainActivity : AppCompatActivity(), OnToolbarAvailableListener {
    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }


    private val settings: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(applicationContext)
    }
    private val questionsSize by lazy {
        settings.getBoolean("iniciado",false
        )
    }

    private val appBarConfiguration: AppBarConfiguration =
        AppBarConfiguration.Builder(
            R.id.dashboardDestination)
            .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onToolbarCreated(toolbar: Toolbar) {
        toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    override fun onToolbarDestroyed() {
    }





}
