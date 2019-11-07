package com.example.triptogalsen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_inscription.*

class InscriptionActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var myDatabaseReference : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        myDatabaseReference = database.reference.child("Users")

        signUp_process.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.signUp_process -> {
                signUp(emailInscription.text.toString(),passwordInputInscription.text.toString())
            }
        }
    }

    private fun signUp(email:String,password:String){
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.i("SIGN UP", "createUserWithEmail:success")
                val userId = auth.currentUser!!.uid
                val currentUserDb = myDatabaseReference.child(userId)
                currentUserDb.child("firstName").setValue(firstName.text.toString())
                currentUserDb.child("lastName").setValue(lastname.text.toString())
                currentUserDb.child("pseudo").setValue(pseudo.text.toString())
                currentUserDb.child("nationality").setValue(nationalite.text.toString())
                currentUserDb.child("birthDate").setValue(birthDate.text.toString())
                Toast.makeText(this, "Inscription reussie", Toast.LENGTH_SHORT).show()
                updateUi()
            } else {
                // If sign in fails, display a message to the user.
                Log.i("SIGN UP", "createUserWithEmail:failure", task.exception)
                Toast.makeText(this, "Inscription failed.",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateUi(){
        val myIntent = SplashActivity.openHomeActivity(this)
        startActivity(myIntent)
        finish()
    }
}
