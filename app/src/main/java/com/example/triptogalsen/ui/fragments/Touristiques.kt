package com.example.triptogalsen.ui.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.triptogalsen.R
import com.example.triptogalsen.api.TripToGalsen
import com.example.triptogalsen.models.Sites
import com.example.triptogalsen.ui.TouristiqueAdapter
import kotlinx.android.synthetic.main.fragment_touristiques.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class Touristiques : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_touristiques, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        TripToGalsen.buildRetrofit().getSites().enqueue(object:
            Callback<List<Sites>> {
            override fun onFailure(call: Call<List<Sites>>, t: Throwable) {
                Toast.makeText(activity, "FAILED LOADING", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Sites>>, response: Response<List<Sites>>) {
                my_touristique_recycler_view.layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL,false)
                my_touristique_recycler_view.adapter = TouristiqueAdapter(response.body()!!)
            }

        })
    }


}
