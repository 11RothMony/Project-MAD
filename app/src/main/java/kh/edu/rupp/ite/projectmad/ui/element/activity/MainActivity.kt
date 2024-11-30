package kh.edu.rupp.ite.projectmad.ui.element.activity

import HomeFragment
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.databinding.ActivityMainBinding

import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val homeFragment = HomeFragment()
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
