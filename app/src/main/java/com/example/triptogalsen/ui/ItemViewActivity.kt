package com.example.triptogalsen.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import com.example.triptogalsen.R
import com.example.triptogalsen.models.Sites
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_item_view.*

class ItemViewActivity : AppCompatActivity() {

    companion object {
        val EXTRA_DESCRIPTION = "description"
        val  EXTRA_IMAGE = "image"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_view)

        setSupportActionBar(my_toolbar)

        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
        val image = intent.getStringExtra(EXTRA_IMAGE)
        Picasso.get().load(image).into(picture_item)
        long_text.text = description
        long_text.movementMethod = ScrollingMovementMethod()
        long_text.isVerticalScrollBarEnabled = false
    }
}
