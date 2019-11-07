package com.example.triptogalsen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_connexion.*
import kotlinx.android.synthetic.main.activity_inscription.*
import kotlin.math.sign

class ConnexionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        auth = FirebaseAuth.getInstance()

        signUp.setOnClickListener {
            val myIntent = Intent(this,InscriptionActivity::class.java)
            startActivity(myIntent)
        }

        signIn.setOnClickListener {
            val emailUser = loginInput.text
            val passwordUser = passwordInput.text

            signIn(emailUser.toString(),passwordUser.toString())
        }


    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        //updateUi(currentUser)
    }

    private fun signIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("SIGN IN", "signInWithEmail:success")
                val user = auth.currentUser
                Toast.makeText(this, "Authentication success.",
                    Toast.LENGTH_SHORT).show()
                updateUi()
            } else {
                // If sign in fails, display a message to the user.
                Log.w("SIGN IN", "signInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUi(){
        val myIntent = Intent(this,HomeActivity::class.java)
        startActivity(myIntent)
    }
}
