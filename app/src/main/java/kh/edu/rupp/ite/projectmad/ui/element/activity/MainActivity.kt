package kh.edu.rupp.ite.projectmad.ui.element.activity

import HomeFragment
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import com.google.firebase.FirebaseApp
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.databinding.ActivityMainBinding
import kh.edu.rupp.ite.projectmad.ui.element.fragment.AccountFragment
import kh.edu.rupp.ite.projectmad.ui.element.fragment.CartFragment
import kh.edu.rupp.ite.projectmad.ui.element.fragment.OrderFragment

import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
    private val cartFragement = CartFragment()
    private val orderFragment = OrderFragment()
    private val accountFragment = AccountFragment()

    private lateinit var activeFragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupFragment()
        setupBottomNavBar()
        FirebaseApp.initializeApp(this)
    }


    private fun setupFragment() {
        if (supportFragmentManager.fragments.isEmpty()) {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            activeFragment = homeFragment

            fragmentTransaction.add(binding.lyFragment.id, homeFragment)
            fragmentTransaction.add(binding.lyFragment.id, cartFragement).hide(cartFragement)
            fragmentTransaction.add(binding.lyFragment.id, orderFragment).hide(orderFragment)
            fragmentTransaction.add(binding.lyFragment.id, accountFragment).hide(accountFragment)

            fragmentTransaction.commit()
        }
    }

    private fun setupBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            handleOnNavigationItemSelected(it)
        }
    }

    private fun handleOnNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuHome -> showFragment(homeFragment)
            R.id.menuCart -> showFragment(cartFragement)
            R.id.menuOrders -> showFragment(orderFragment)
            R.id.menuAccount -> showFragment(accountFragment)
        }
        return true
    }

    private fun showFragment(fragment: Fragment) {
        if (activeFragment == fragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (!fragment.isAdded) {
            // Add the fragment if it's not already added
            removeCurrentFragment()
            fragmentTransaction.replace(R.id.lyFragment, fragment)

        }
        // Hide the currently active fragment
        fragmentTransaction.hide(activeFragment)

        // Show the new fragment
        fragmentTransaction.show(fragment)

        // Update the active fragment reference
        activeFragment = fragment

        fragmentTransaction.commit()
    }


    private fun removeCurrentFragment() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.lyFragment)
        if (currentFragment != null) {
            supportFragmentManager.beginTransaction()
                .remove(currentFragment)
                .commit()
        }
    }


}
