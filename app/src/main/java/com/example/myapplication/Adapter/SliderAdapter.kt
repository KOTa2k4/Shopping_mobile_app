package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Model.SliderModel
import com.example.myapplication.databinding.SliderItemContainerBinding
import android.content.Context

class SliderAdapter(
    private var sliderItems:List<SliderModel>,
    private val viewPager2: ViewPager2
): RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private lateinit var context: Context
    private val runnable= Runnable {
        sliderItems=sliderItems
        notifyDataSetChanged()
    }

    class SliderViewHolder(private val binding: SliderItemContainerBinding):
    RecyclerView.ViewHolder(binding.root){
        fun setImage(sliderModel: SliderModel,context: Context){
            Glide.with(context)
                .load(sliderModel.url)
                .apply(RequestOptions().transform(CenterInside()))
                .into(binding.imageSlide)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
        context=parent.context
        val binding= SliderItemContainerBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderAdapter.SliderViewHolder, position: Int) {
        holder.setImage(sliderItems[position], context)
        if(position==sliderItems.lastIndex-1){
            viewPager2.post (runnable)
        }
    }

    override fun getItemCount(): Int = sliderItems.size
}