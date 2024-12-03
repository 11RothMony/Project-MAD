package kh.edu.rupp.ite.projectmad.ui.element.activity

import HomeFragment
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
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

    }

    private fun setupFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        activeFragment = homeFragment

        fragmentTransaction.add(binding.lyFragment.id, homeFragment)
        fragmentTransaction.add(binding.lyFragment.id, cartFragement).hide(cartFragement)
        fragmentTransaction.add(binding.lyFragment.id, orderFragment).hide(orderFragment)
        fragmentTransaction.add(binding.lyFragment.id, accountFragment).hide(accountFragment)

        fragmentTransaction.commit()
    }

    private fun setupBottomNavBar() {
        binding.bottomNavigation.setOnItemSelectedListener {
            handleOnNavigationItemSelected(it)
        }
    }

    private fun handleOnNavigationItemSelected(item: MenuItem): Boolean{
        when (item.itemId){
            R.id.menuHome -> showFragement(homeFragment)
            R.id.menuCart -> showFragement(cartFragement)
            R.id.menuOrders -> showFragement(orderFragment)
            R.id.menuAccount -> showFragement(accountFragment)

        }
        return true
    }

    private fun showFragement(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.hide(activeFragment)
        fragmentTransaction.show(fragment)
        activeFragment = fragment
        fragmentTransaction.commit()
    }

}
