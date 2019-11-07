package com.example.triptogalsen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signOut.setOnClickListener(this)

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.signOut -> signOut()
        }
    }

     fun signOut(){
        Log.i("CLICK","DECONNECTER")
        FirebaseAuth.getInstance().signOut()
        val myIntent = SplashActivity.openConnexionActivity(this)
        startActivity(myIntent)
        finish()
    }
}
