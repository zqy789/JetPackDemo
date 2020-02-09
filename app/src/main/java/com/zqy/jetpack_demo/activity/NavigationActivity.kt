package com.zqy.jetpack_demo.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zqy.jetpack_demo.R
import kotlinx.android.synthetic.main.activity_navigation.*

class NavigationActivity : AppCompatActivity(),
    BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var findNavController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        findNavController = Navigation.findNavController(this,
            R.id.navigationPage
        )
        navigationView.setupWithNavController(findNavController)
        findNavController.addOnDestinationChangedListener { controller, destination, arguments ->
            println("NavigationActivity$destination")
        }
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        val itemId = p0.itemId
        if (itemId == R.id.menu_home) {
            findNavController.navigate(R.id.home_fragment_sample)
        } else {
            findNavController.navigate(R.id.view_model_sample)
        }
        return true
    }
}
