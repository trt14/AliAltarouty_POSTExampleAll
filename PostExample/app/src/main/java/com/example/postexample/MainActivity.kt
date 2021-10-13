package com.example.postexample

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.postexample.databinding.ActivityMainBinding

import com.example.postexample.NewUser
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var rvMain: RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this,NewUser::class.java )
            startActivity(intent)
        }

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this,UpdateActivity::class.java )
            startActivity(intent)
        }
        binding.btndelete.setOnClickListener {
            val intent = Intent(this,DeleteActivity::class.java )
            startActivity(intent)
        }




        lifecycleScope.launchWhenCreated {
            val response = try{
                RetrofitInstance.api.getData()
            }catch (e: IOException){
                Log.d("Main-Error", e.message.toString())
                return@launchWhenCreated

            }catch (e: HttpException){
                Log.d("Main-Error", e.message.toString())
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() !=null){
                rvMain.items = response.body()!!
            } else{
                Log.d("Main-Error", "Response not successful")
            }
        }





    }


    private fun setupRV()= binding.rvMain.apply {
        rvMain = RVAdapter()
        adapter = rvMain
        layoutManager = GridLayoutManager(this@MainActivity, 2)
    }



}