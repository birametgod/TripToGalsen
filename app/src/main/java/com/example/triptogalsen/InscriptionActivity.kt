package com.example.triptogalsen

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.wajahatkarim3.easyvalidation.core.view_ktx.*
import kotlinx.android.synthetic.main.activity_inscription.*
import java.text.SimpleDateFormat
import java.util.*

class InscriptionActivity : AppCompatActivity() , View.OnClickListener{

    private lateinit var auth: FirebaseAuth
    private lateinit var database : FirebaseDatabase
    private lateinit var myDatabaseReference : DatabaseReference
    private lateinit var cal : Calendar
    private lateinit var dateSetListener : DatePickerDialog.OnDateSetListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inscription)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        myDatabaseReference = database.reference.child("Users")
        cal = Calendar.getInstance()

        signUp_process.setOnClickListener(this)
        calendarInscription.setOnClickListener(this)

        // create an OnDateSetListener
         dateSetListener = object : DatePickerDialog.OnDateSetListener {

            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd/MM/yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
                val date = birthDate as TextView
                date.text = sdf.format(cal.time)
            }

        }

    }

    override fun onClick(view: View) {
        when(view.id){
            R.id.calendarInscription -> {
                DatePickerDialog(this,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
            R.id.signUp_process -> {
                val isPseudoValid = pseudo.minLength(5){
                    pseudo.error = "doit etre superieur à 5"
                }

                val isFirstNameValid = firstName.nonEmpty{
                    firstName.error = "ne doit pas être vide"
                }

                val isLastNameValid = lastname.nonEmpty{
                    lastname.error = "ne doit pas être vide"
                }

                val isEmailValid = emailInscription.validEmail {
                    emailInscription.error = "Mail Invalide"
                }

                var isPasswordValid = false

                passwordInputInscription
                    .validator()
                    .nonEmpty()
                    .minLength(8)
                    .atleastOneNumber()
                    .atleastOneSpecialCharacters()
                    .atleastOneUpperCase()
                    .addErrorCallback {
                        passwordInputInscription.error = it
                    }
                    .addSuccessCallback {
                        isPasswordValid = true
                    }
                    .check()

                val isNationalityValid = nationalite.nonEmpty{
                    nationalite.error = "ne doit pas être vide"
                }

                if (isPseudoValid && isNationalityValid && isFirstNameValid && isEmailValid && isLastNameValid && isPasswordValid){
                    signUp(emailInscription.text.toString(),passwordInputInscription.text.toString())
                }

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
