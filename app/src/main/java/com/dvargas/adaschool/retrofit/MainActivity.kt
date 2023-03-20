package com.dvargas.adaschool.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dvargas.adaschool.retrofit.network.DogsImageService
import com.dvargas.adaschool.retrofit.repository.DogsImageRepository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dogsImageService = DogsImageRepository.create()
        getRandomDogByDogBreed(dogsImageService, "boxer")
        getRandomDog(dogsImageService)

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getRandomDogByDogBreed(dogsImageService: DogsImageService, dogBreed: String) {
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val imageView = findViewById<ImageView>(R.id.imageView)
                val response = dogsImageService.getRandomImageByDogBreed(dogBreed)
                if (response.isSuccessful) {
                    val randomDogImageDto = response.body()
                    Log.d("Developer", "Response:  $randomDogImageDto")
                    runOnUiThread {
                        Picasso.get().load(randomDogImageDto!!.message).into(imageView)
                    }
                }
            }
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getRandomDog(dogsImageService: DogsImageService) {
        val button = findViewById<Button>(R.id.button2)
        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                val imageView = findViewById<ImageView>(R.id.imageView)
                val response = dogsImageService.getRandomDogImage()
                if (response.isSuccessful) {
                    val randomDogImageDto = response.body()
                    Log.d("Developer", "Response:  $randomDogImageDto")
                    runOnUiThread {
                        Picasso.get().load(randomDogImageDto!!.message).into(imageView)
                    }
                }
            }
        }
    }
}