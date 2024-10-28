package kh.edu.rupp.ite.projectmad

import Fragment.FragmentLogin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = FragmentLogin()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLogin, fragment)
            .commit()
    }
}