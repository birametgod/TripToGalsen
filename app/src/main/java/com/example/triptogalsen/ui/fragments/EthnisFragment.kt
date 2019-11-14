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
import com.example.triptogalsen.models.EthnisModel
import com.example.triptogalsen.ui.EthnisAdapter
import com.example.triptogalsen.ui.ItemViewActivity
import kotlinx.android.synthetic.main.fragment_ethnis.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 */
class EthnisFragment : Fragment(), View.OnClickListener {

    private lateinit var ethnis : List<EthnisModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ethnis, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        TripToGalsen.buildRetrofit().getEthnis().enqueue(object:
            Callback<List<EthnisModel>> {
            override fun onFailure(call: Call<List<EthnisModel>>, t: Throwable) {
                Toast.makeText(activity,"FAILED",Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<EthnisModel>>, response: Response<List<EthnisModel>>) {
                ethnis = response.body()!!
                ethnis_recycler_view.layoutManager = LinearLayoutManager(activity,
                    LinearLayoutManager.HORIZONTAL,false)
                ethnis_recycler_view.adapter = EthnisAdapter(response.body()!!,this@EthnisFragment)
            }

        })
    }
    override fun onClick(view: View?) {
        val index = view?.tag as Int
        val ethnie = ethnis[index]
        val intent = Intent(activity, ItemViewActivity::class.java)
        intent.putExtra(ItemViewActivity.EXTRA_DESCRIPTION,ethnie.description)
        intent.putExtra(ItemViewActivity.EXTRA_IMAGE,ethnie.image)
        startActivity(intent)
    }


}
