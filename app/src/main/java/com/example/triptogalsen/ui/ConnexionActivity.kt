package com.example.triptogalsen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.triptogalsen.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.wajahatkarim3.easyvalidation.core.view_ktx.nonEmpty
import com.wajahatkarim3.easyvalidation.core.view_ktx.validEmail
import kotlinx.android.synthetic.main.activity_connexion.*

class ConnexionActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connexion)

        auth = FirebaseAuth.getInstance()
        signIn.setOnClickListener(this)
        signUp.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()

        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUi(currentUser)
        }
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.signIn -> {
                val isPasswordValid = passwordInput.nonEmpty(){
                    passwordInput.error = "Ne doit pas être vide"
                }

                val isEmailValid = loginInput.validEmail {
                    loginInput.error = "Mail invalid"
                }

                if (isPasswordValid && isEmailValid){
                    signIn(loginInput.text.toString(),passwordInput.text.toString())
                }

            }
            R.id.signUp -> {
               val myIntent =
                   SplashActivity.openInscriptionActivity(this)
                startActivity(myIntent)
            }
        }
    }

    private fun signIn(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d("SIGN IN", "signInWithEmail:success")
                val user = auth.currentUser
                Toast.makeText(this, "Authentication success.",
                    Toast.LENGTH_SHORT).show()
                updateUi(user)
            } else {
                // If sign in fails, display a message to the user.
                Log.w("SIGN IN", "signInWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUi(user: FirebaseUser?){
        Log.i("USER", "my user : ${user?.email}")
        val myIntent = SplashActivity.openHomeActivity(this)
        startActivity(myIntent)
        finish()
    }
}
