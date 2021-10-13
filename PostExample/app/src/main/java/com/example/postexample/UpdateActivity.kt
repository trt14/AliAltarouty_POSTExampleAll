package com.example.postexample

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.postexample.RetrofitInstance.api
import com.example.postexample.databinding.ActivityUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateActivity: AppCompatActivity()   {

    var userID = ""
    var userName = ""
    var userLocation = ""
    lateinit var binding: ActivityUpdateBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnUpdate.setOnClickListener {
            update()
            clear()

        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun update(){
        userID= binding.etPk.text.toString()
        userName= binding.etUserName.text.toString()
        userLocation= binding.etUserLocation.text.toString()
        api.update(
            userID,
            Data(userID, userName, userLocation)
        ).enqueue(object : Callback<Data?> {
            override fun onResponse(call: Call<Data?>, response: Response<Data?>) {
                Toast.makeText(applicationContext, "User was updated Successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Data?>, t: Throwable) {
                Toast.makeText(applicationContext, "User was not updated Successfully", Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun clear(){
        binding.etPk.text.clear()
        binding.etUserName.text.clear()
        binding.etUserLocation.text.clear()
    }
}
