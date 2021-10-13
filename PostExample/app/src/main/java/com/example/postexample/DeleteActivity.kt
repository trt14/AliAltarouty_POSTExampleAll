package com.example.postexample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.postexample.RetrofitInstance.api
import com.example.postexample.databinding.ActivityDeleteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class DeleteActivity : AppCompatActivity()  {
    var userID = ""
    var userName = ""
    var userLocation = ""
    lateinit var binding: ActivityDeleteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.btnDel.setOnClickListener {
            delete()
            clear()

        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun delete(){
        userID= binding.etPk.text.toString()
        api?.deleteUser(
            userID
        )?.enqueue(object : Callback<Void?> {
            override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                Toast.makeText(applicationContext, "User was deleted Successfully", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(call: Call<Void?>, t: Throwable) {
                Toast.makeText(applicationContext, "User was not deleted Successfully", Toast.LENGTH_LONG).show()
            }
        })

    }


    private fun clear(){
        binding.etPk.text.clear()
    }
    }