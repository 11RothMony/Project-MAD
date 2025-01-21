package kh.edu.rupp.ite.projectmad.ui.element.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.projectmad.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Close splash activity
        }, 2000) // 2 seconds delay
    }
}