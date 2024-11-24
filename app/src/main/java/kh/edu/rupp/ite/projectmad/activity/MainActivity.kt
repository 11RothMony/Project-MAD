package kh.edu.rupp.ite.projectmad.activity

import android.app.Activity
import kh.edu.rupp.ite.projectmad.fragment.FragmentLogin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.fragment.FragmentMenu

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setContentView(R.layout.activity_menu)


        val fragment = FragmentMenu()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMenu, fragment)
            .commit()

    }
}