package com.example.triptogalsen.ui.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.triptogalsen.R
import com.example.triptogalsen.api.TripToGalsen
import com.example.triptogalsen.models.CloutLocationModel
import com.example.triptogalsen.ui.FamousPictureAdapter
import com.example.triptogalsen.ui.ItemViewActivity
import kotlinx.android.synthetic.main.fragment_famous_picture.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class FamousPictureFragment : Fragment(), View.OnClickListener {

    private lateinit var famousPicture: List<CloutLocationModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_famous_picture, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        TripToGalsen.buildRetrofit().getCloutLocation().enqueue(object:
            Callback<List<CloutLocationModel>> {
            override fun onFailure(call: Call<List<CloutLocationModel>>, t: Throwable) {
                Toast.makeText(activity, "FAILED LOADING", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<CloutLocationModel>>, response: Response<List<CloutLocationModel>>) {
                famousPicture = response.body()!!.reversed()
                my_picture_recycler_view.layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL,false)
                my_picture_recycler_view.adapter = FamousPictureAdapter(response.body()!!.reversed(),this@FamousPictureFragment)
            }

        })
    }

    override fun onClick(view: View?) {
        val index = view?.tag as Int
        val famous = famousPicture[index]
        val intent = Intent(activity, ItemViewActivity::class.java)
        intent.putExtra(ItemViewActivity.EXTRA_DESCRIPTION,famous.name)
        intent.putExtra(ItemViewActivity.EXTRA_IMAGE,famous.image)
        startActivity(intent)
    }


}
