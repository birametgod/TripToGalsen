package com.example.triptogalsen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat.startActivity

class SplashActivity : AppCompatActivity() {

    companion object {
        fun openConnexionActivity(context: Context) : Intent{
            val myIntent = Intent(context,ConnexionActivity::class.java)
            return myIntent
        }

        fun openInscriptionActivity(context: Context): Intent{
            val myIntent = Intent(context,InscriptionActivity::class.java)
            return myIntent
        }

        fun openHomeActivity(context: Context) : Intent{
            val myIntent = Intent(context,HomeActivity::class.java)
            return myIntent
        }
    }

    private lateinit var myHandler: Handler
    private  val splashTime = 3000L
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        myHandler = Handler()
        myHandler.postDelayed({
            val myIntent = Companion.openConnexionActivity(this)
            startActivity(myIntent)
            finish()
        },splashTime)
    }

}
