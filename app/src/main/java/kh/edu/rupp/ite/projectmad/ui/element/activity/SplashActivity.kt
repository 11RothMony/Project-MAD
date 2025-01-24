package kh.edu.rupp.ite.projectmad.ui.element.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kh.edu.rupp.ite.projectmad.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_screen)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = android.view.Gravity.CENTER
            setBackgroundColor(Color.WHITE)
        }

        val textView = TextView(this).apply {
            text = "Welcome to MyApp"
            textSize = 24f
            setTextColor(Color.BLACK)
        }

        layout.addView(textView)
        setContentView(layout)

        Handler().postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // Close splash activity
        }, 2000) // 2 seconds delay
    }
}