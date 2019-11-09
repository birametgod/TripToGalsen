package com.example.triptogalsen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var bottomNavigation : BottomNavigationView

    private val myNavigationClickListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when(it.itemId){
            R.id.navigation_home -> {
                Log.i("Home", "clique home")
                val fragment = HomeFragment()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dico -> {
                Log.i("Dico", "clique Dico")
                val fragment = DicoFragment()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_culture -> {
                Log.i("Culture", "clique Culture")
                val fragment = CultureFragment()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_profil -> {
                Log.i("profil", "clique profil")
                val fragment = ProfilFragment()
                openFragment(fragment)
                return@OnNavigationItemSelectedListener true
            }
            else -> false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //signOut.setOnClickListener(this)
        setSupportActionBar(my_toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        bottomNavigation = bottomNavigationView
        bottomNavigation.setOnNavigationItemSelectedListener(myNavigationClickListener)

        if (savedInstanceState == null){
            val fragment = HomeFragment()
            openFragment(fragment)
        }

    }

    override fun onClick(view: View) {

    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.containerFrame, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
