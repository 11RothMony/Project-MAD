package kh.edu.rupp.ite.projectmad.ui.element.activity

import android.app.Activity
import kh.edu.rupp.ite.projectmad.ui.element.fragment.FragmentLogin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kh.edu.rupp.ite.projectmad.R
import kh.edu.rupp.ite.projectmad.ui.element.fragment.FragmentMenu
import kh.edu.rupp.ite.visitme.ui.element.activity.BaseActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val fragment = FragmentMenu()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentMenu, fragment)
            .commit()

    }
}