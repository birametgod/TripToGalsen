package com.example.triptogalsen.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.triptogalsen.R
import com.example.triptogalsen.api.TripToGalsen
import com.example.triptogalsen.models.CloutLocationModel
import com.example.triptogalsen.ui.CloutLocationAdapter
import kotlinx.android.synthetic.main.fragment_clout_location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class CloutLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_clout_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TripToGalsen.buildRetrofit().getCloutLocation().enqueue(object:
            Callback<List<CloutLocationModel>> {
            override fun onFailure(call: Call<List<CloutLocationModel>>, t: Throwable) {
                Log.i("failed","YES  $")
            }

            override fun onResponse(call: Call<List<CloutLocationModel>>, response: Response<List<CloutLocationModel>>) {
                Log.i("failed","YES REPONSE ${response.body()}")
                my_recycler_view.layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL,false)
                my_recycler_view.adapter = CloutLocationAdapter(response.body()!!)
            }

        })
    }


}
