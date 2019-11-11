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
import com.example.triptogalsen.models.Sites
import com.example.triptogalsen.ui.SitesAdapter
import kotlinx.android.synthetic.main.fragment_sites.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class SitesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TripToGalsen.buildRetrofit().getSites().enqueue(object:
            Callback<List<Sites>> {
            override fun onFailure(call: Call<List<Sites>>, t: Throwable) {
                Log.i("failed","YES  $")
            }

            override fun onResponse(call: Call<List<Sites>>, response: Response<List<Sites>>) {
                Log.i("failed","YES REPONSE ${response.body()}")
                my_sites_recycler_view.layoutManager = LinearLayoutManager(activity,
                  LinearLayoutManager.HORIZONTAL,false)
                my_sites_recycler_view.adapter = SitesAdapter(response.body()!!)
            }

        })
    }


}
