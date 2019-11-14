package com.example.triptogalsen.ui.fragments


import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.triptogalsen.R
import com.example.triptogalsen.models.User
import com.example.triptogalsen.ui.ConnexionActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_profil.view.*

/**
 * A simple [Fragment] subclass.
 */
class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profil, container, false)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        val myDatabaseReferenceUser = FirebaseDatabase.getInstance().reference.child("Users")
        val currentUserDb = myDatabaseReferenceUser.child(userId)
        currentUserDb.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

               val firstName = dataSnapshot.child("firstName").getValue(String::class.java)
                val lastName = dataSnapshot.child("lastName").getValue(String::class.java)
                val nationality =  dataSnapshot.child("nationality").getValue(String::class.java)
                val email = FirebaseAuth.getInstance().currentUser!!.email

                view.name_text.text = "$firstName $lastName"
                view.email_text.text = "$email"
                view.nationality_text.text = "$nationality"
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })

        view.signOut.setOnClickListener {
            Log.i("CLICK","DECONNECTER")
            FirebaseAuth.getInstance().signOut()
            val myIntent = Intent(activity, ConnexionActivity::class.java)
            startActivity(myIntent)
        }

        return view
    }

}
