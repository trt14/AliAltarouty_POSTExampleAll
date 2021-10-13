package com.example.postexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.postexample.RetrofitInstance.api
import com.example.postexample.databinding.ActivityNewUserBinding

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewUser : AppCompatActivity() {
    lateinit var binding: ActivityNewUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnView.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java )
            startActivity(intent)
        }



        binding.btnSend.setOnClickListener {

            postData()
            clear()
        }

    }

    private fun postData(){
        val name = binding.etUserName.text.toString()
        val location = binding.etUserLocation.text.toString()

        api.postData(Data("",name, location)).enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>){
                Log.d("Main-success", "Data Added")
            }
            override fun onFailure(call: Call<Data>, t: Throwable){
                Log.d("Main-Failure", "Data Not Added")
            }
        })

    }

    private fun clear(){
        binding.etUserLocation.text.clear()
        binding.etUserLocation.text.clear()
    }
}