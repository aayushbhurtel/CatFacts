package com.example.catfacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

const val BASE_URL = "https://cat-fact.herokuapp.com"
val TAG = "MainActivity"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getCurrentData()
        getCatImages()

        button.setOnClickListener {
            getCurrentData()
            getCatImages()
        }
    }

    private fun getCatImages() {
        val height = Random.nextInt(0, 9)
        val width = Random.nextInt(0, 9)
        val url = "https://placekitten.com/5$width$height/5$height$width"

        with(applicationContext)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .skipMemoryCache(true)
            .into(catImages)
    }

    private fun getCurrentData() {
        tv_textView.visibility = View.GONE
        tv_timeStamp.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCatFacts().awaitResponse()
                if (response.isSuccessful) {

                    val data = response.body()!!
                    Log.d(TAG, data.toString())

                    withContext(Dispatchers.Main) {
                        tv_textView.visibility = View.VISIBLE
                        tv_timeStamp.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        tv_timeStamp.text = data.createdAt
                        tv_textView.text = data.text

                    }

                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        applicationContext,
                        "Seems like something went wrong...",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }

    }
}