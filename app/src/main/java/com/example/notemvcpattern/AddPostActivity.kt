package com.example.notemvcpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.notemvcpattern.databinding.ActivityAddPostBinding
import com.example.notemvcpattern.model.Poster
import com.example.notemvcpattern.model.PosterResp
import com.example.notemvcpattern.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class AddPostActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddPostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPostBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {

        binding.btnAdd.setOnClickListener {
            val tv_theme = binding.etTheme.text.toString()
            val tv_title = binding.etTitle.text.toString()
            val calendar = Calendar.getInstance()
            val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
            val dateTime = simpleDateFormat.format(calendar.time).toString()

            val poster = Poster(1, tv_theme, tv_title, dateTime)
            RetrofitHttp.postService.createPost(poster).enqueue(object : Callback<PosterResp> {
                override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                    finish()
                }

                override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                }

            })
        }

    }
}