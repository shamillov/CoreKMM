package com.shamilov.androidNative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.shamilov.core.Greeting
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.shamilov.androidNative.ui.card.CardFragment
import com.shamilov.androidNative.ui.category.CategoryFragment
import com.shamilov.androidNative.ui.main.MainFragment
import com.shamilov.androidNative.ui.profile.ProfileFragment

/**
 * Created by Shamilov on 24.05.2021
 */
fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity(R.layout.activity_main), OnBadgeListener {

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupNavigationView()
    }

    override fun showBadge(show: Boolean) {
        val menu = navView.menu
        val badge = navView.getOrCreateBadge(menu.getItem(2).itemId)

        badge.isVisible = show
    }

    private fun setupNavigationView() {
        navView = findViewById(R.id.nav_view)
        navView.elevation = 0f
        navView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.main -> handleFragmentCreating(MainFragment())
                R.id.category -> handleFragmentCreating(CategoryFragment())
                R.id.card -> handleFragmentCreating(CardFragment())
                R.id.profile -> handleFragmentCreating(ProfileFragment())

                else -> false
            }
        }
    }

    private fun handleFragmentCreating(fragment: Fragment): Boolean {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment).commit()

        return true
    }
}

interface OnBadgeListener {
    fun showBadge(show: Boolean)
}
