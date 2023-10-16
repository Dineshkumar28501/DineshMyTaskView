package com.sgs.mytaskview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sgs.mytaskview.database.LoginDataBase
import com.sgs.mytaskview.database.LoginFactory
import com.sgs.mytaskview.database.LoginRepo
import com.sgs.mytaskview.database.LoginViewModel
import com.sgs.mytaskview.databinding.ActivityDetailsViewBinding

class DetailsView : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsViewBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var adapter : LoginAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repo = LoginRepo(LoginDataBase.getDatabase(this@DetailsView))
        val factory = LoginFactory(repo)
        viewModel = ViewModelProvider(this,factory)[LoginViewModel::class.java]

        addShow()

    }

    private fun addShow(){
        lifecycleScope.launchWhenStarted {
            viewModel.getMaterialListSiteWise()!!.collect{

                adapter= LoginAdapter()
                binding.recycler.adapter=adapter
                binding.recycler.layoutManager = LinearLayoutManager(this@DetailsView)
                adapter.differ.submitList(it)


                Log.i("TAG", "addShow:$it")

            }
        }
    }

}