package es.iessaladillo.pedrojoya.stroop.ui


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import es.iessaladillo.pedrojoya.stroop.R
import es.iessaladillo.pedrojoya.stroop.base.OnToolbarAvailableListener


class MainActivity : AppCompatActivity(), OnToolbarAvailableListener {
    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    private val appBarConfiguration: AppBarConfiguration =
        AppBarConfiguration.Builder(
            R.id.dashboardDestination
        )
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
