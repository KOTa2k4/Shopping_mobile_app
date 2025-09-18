package com.example.myapplication.Activity

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.ListFilteredAdapter
import com.example.myapplication.R
import com.example.myapplication.ViewModel.MainViewModel
import com.example.myapplication.databinding.ActivityListItemsBinding

class ListItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListItemsBinding
    private var viewModel= MainViewModel()
    private var id: String = ""
    private var _title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            , WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            binding.backBtn.setOnClickListener { finish() }
            progressBarList.visibility= View.VISIBLE
            viewModel.popular.observe(this@ListItemsActivity, Observer{
                viewList.layoutManager= GridLayoutManager(this@ListItemsActivity,2)
                viewList.adapter= ListFilteredAdapter(it)
                progressBarList.visibility=View.GONE
            })
        }
        viewModel.loadFiltered(id)
    }

    private fun getBundle() {
        id=intent.getStringExtra("id")!!
        _title=intent.getStringExtra("title")!!

        binding.categoryTxt.text=_title
    }
}