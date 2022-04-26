package com.example.notemvcpattern

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notemvcpattern.adapter.PostAdapter
import com.example.notemvcpattern.databinding.ActivityMainBinding
import com.example.notemvcpattern.model.Poster
import com.example.notemvcpattern.model.PosterResp
import com.example.notemvcpattern.networking.RetrofitHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onResume() {
        super.onResume()
        allPoster()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.recyclerView.layoutManager = GridLayoutManager(this, 1)

        allPoster()

        binding.ivAdd.setOnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshAdapter(posts: ArrayList<PosterResp>) {
        val adapter = PostAdapter(this, posts)
        binding.recyclerView.adapter = adapter
    }

    private fun allPoster() {
        RetrofitHttp.postService.listPost().enqueue(object : Callback<ArrayList<PosterResp>>{
            override fun onResponse(
                call: Call<ArrayList<PosterResp>>,
                response: Response<ArrayList<PosterResp>>,
            ) {
                refreshAdapter(response.body() as ArrayList<PosterResp>)
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {

            }

        })
    }

    fun postDelete(posterResp: PosterResp){
        RetrofitHttp.postService.deletePost(posterResp.id!!).enqueue(object : Callback<PosterResp>{
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                allPoster()
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {

            }

        })
    }

}