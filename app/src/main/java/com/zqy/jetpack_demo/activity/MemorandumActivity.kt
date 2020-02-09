package com.zqy.jetpack_demo.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.zqy.jetpack_demo.R
import kotlinx.android.synthetic.main.activity_memor_randum.*


/**
 * 备忘录
 */
class MemorandumActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memor_randum)


        setSupportActionBar(toolBarMemo)

        navController = Navigation.findNavController(this, R.id.memorandumNav)

        appBarConfiguration =
            AppBarConfiguration.Builder(navController.graph).setDrawerLayout(drawer).build()
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
//        drawerMenu.setupWithNavController(navController)
    }

    /**
     * 左上角的菜单被点击时调用到
     */
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            navController,
            appBarConfiguration
        ) || super.onSupportNavigateUp()
    }
}
