package com.example.triptogalsen.ui.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.triptogalsen.R
import com.example.triptogalsen.api.TripToGalsen
import com.example.triptogalsen.models.GastroModel
import com.example.triptogalsen.ui.GastroAdapter
import com.example.triptogalsen.ui.ItemViewActivity
import kotlinx.android.synthetic.main.fragment_gastro.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class GastroFragment : Fragment(), View.OnClickListener {

    private lateinit var gastronomie: List<GastroModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gastro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        TripToGalsen.buildRetrofit().getGastro().enqueue(object:
            Callback<List<GastroModel>> {
            override fun onFailure(call: Call<List<GastroModel>>, t: Throwable) {
                Toast.makeText(activity, "FAILED LOADING", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<GastroModel>>, response: Response<List<GastroModel>>) {
                gastronomie = response.body()!!
                my_gastro_recycler_view.layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL,false)
                my_gastro_recycler_view.adapter = GastroAdapter(response.body()!!,this@GastroFragment)
            }

        })
    }

    override fun onClick(view: View?) {
        val index = view?.tag as Int
        val gastro = gastronomie[index]
        val intent = Intent(activity, ItemViewActivity::class.java)
        intent.putExtra(ItemViewActivity.EXTRA_DESCRIPTION,gastro.description)
        intent.putExtra(ItemViewActivity.EXTRA_IMAGE,gastro.image)
        startActivity(intent)
    }


}
