package com.example.triptogalsen


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
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
        view.signOut.setOnClickListener {
            Log.i("CLICK","DECONNECTER")
            FirebaseAuth.getInstance().signOut()
            val myIntent = Intent(activity,ConnexionActivity::class.java)
            startActivity(myIntent)
        }

        return view
    }

}
