package com.lowjungxuan.kotlinexercise.drawer

import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.lowjungxuan.kotlinexercise.R
import com.lowjungxuan.kotlinexercise.databinding.DrawerActivityBinding
import com.lowjungxuan.kotlinexercise.utils.SocketHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DrawerActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: DrawerActivityBinding
    private val headerList: ArrayList<ExpandedMenuModel> = ArrayList()
    private val childList: HashMap<ExpandedMenuModel, ArrayList<ExpandedMenuModel>> = HashMap()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DrawerActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarDrawer.toolbar)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_fragment_student,
                R.id.nav_fragment_service,
                R.id.nav_content_provider,
                R.id.nav_intent_implicit,
                R.id.nav_notification
            ), binding.drawerLayout
        )
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_drawer) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        //TODO: Header List
        headerList.apply {
            add(ExpandedMenuModel("Room", R.drawable.ic_baseline_storage_24, R.id.nav_fragment_student))
            add(ExpandedMenuModel("Service", R.drawable.ic_round_electrical_services_24, R.id.nav_fragment_service))
            add(ExpandedMenuModel("Content Provider", R.drawable.ic_baseline_settings_24, R.id.nav_content_provider))
            add(ExpandedMenuModel("Intent Implicit", R.drawable.ic_baseline_language_24, R.id.nav_intent_implicit))
            add(ExpandedMenuModel("Notification", R.drawable.ic_baseline_notifications_24, R.id.nav_notification))
        }

        //TODO: Setup Drawer List
        binding.apply {
            expandedListView.setAdapter(ExpandedMenuAdapter(this@DrawerActivity, headerList, childList))
            expandedListView.choiceMode = ExpandableListView.CHOICE_MODE_SINGLE
            //TODO: Drawer List setOnClickListener
            expandedListView.setOnGroupClickListener { parent, _, groupPosition, _ ->
                drawerLayout.closeDrawer(GravityCompat.START)
                findNavController(R.id.nav_host_fragment_content_drawer).navigate(headerList[groupPosition].location)
                true
            }
        }

        //TODO: Socket Initialize
        SocketHandler.setSocket()
        SocketHandler.establishConnection()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_drawer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun showToast(str: String) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}