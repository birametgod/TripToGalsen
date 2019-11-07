package com.example.triptogalsen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    private lateinit var myHandler: Handler
    private  val splashTime = 3000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        myHandler = Handler()
        myHandler.postDelayed({
            openConnexionActivity()
        },splashTime)
    }

    fun openConnexionActivity(){
        val myIntent = Intent(this,ConnexionActivity::class.java)
        startActivity(myIntent)
        finish()
    }

}
