package kh.edu.rupp.ite.projectmad.activity

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.databinding.ActivityMainBinding
import kh.edu.rupp.ite.projectmad.fragment.account
import kh.edu.rupp.ite.projectmad.fragment.cart
import kh.edu.rupp.ite.projectmad.fragment.home
import kh.edu.rupp.ite.projectmad.fragment.order

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showFragment(home())

        //create home fragment
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> showFragment(home())
                R.id.menuCart -> showFragment(cart())
                R.id.menuOrders -> showFragment(order())
                R.id.menuAccount -> showFragment(account())

                else ->{
                }
            }
            true
        }
    }
    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.home, fragment)
        fragmentTransaction.commit()
    }
}