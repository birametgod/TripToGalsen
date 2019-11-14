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
import com.example.triptogalsen.models.ReligiousModel
import com.example.triptogalsen.ui.EthnisAdapter
import com.example.triptogalsen.ui.ReligiousLocationAdapter
import kotlinx.android.synthetic.main.fragment_religious_location.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class ReligiousLocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_religious_location, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refreshLayoutReligiousLocation.isRefreshing = true
        TripToGalsen.buildRetrofit().getReligiousLocation().enqueue(object:
            Callback<List<ReligiousModel>> {
            override fun onFailure(call: Call<List<ReligiousModel>>, t: Throwable) {
                refreshLayoutReligiousLocation.isRefreshing = false
                Toast.makeText(activity,"FAILED", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<ReligiousModel>>, response: Response<List<ReligiousModel>>) {
                refreshLayoutReligiousLocation.isRefreshing = false
                religious_location_recycler_view.layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL,false)
                religious_location_recycler_view.adapter = ReligiousLocationAdapter(response.body()!!)
            }

        })
    }

}
