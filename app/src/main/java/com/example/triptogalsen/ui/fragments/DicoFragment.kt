package com.example.triptogalsen.ui.fragments


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triptogalsen.R
import com.example.triptogalsen.api.TripToGalsen
import com.example.triptogalsen.models.DicoModel
import com.example.triptogalsen.ui.DicoAdapter
import kotlinx.android.synthetic.main.fragment_dico.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class DicoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dico, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        refreshLayoutDicoLocation.isRefreshing = true

        TripToGalsen.buildRetrofit().getDico().enqueue(object:
            Callback<List<DicoModel>> {
            override fun onFailure(call: Call<List<DicoModel>>, t: Throwable) {
                refreshLayoutDicoLocation.isRefreshing = false
                Toast.makeText(activity, "FAILED LOADING", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<DicoModel>>, response: Response<List<DicoModel>>) {
                refreshLayoutDicoLocation.isRefreshing = false
                dico_recycler_view.layoutManager = LinearLayoutManager(activity)
                dico_recycler_view.adapter = DicoAdapter(response.body()!!)
            }

        })

    }


}
