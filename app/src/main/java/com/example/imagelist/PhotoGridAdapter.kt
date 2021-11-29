package com.example.imagelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imagelist.databinding.GridImagesBinding
import com.example.imagelist.viewmodel.Photos

class PhotoGridAdapter(var photoList: List<Photos>, var clicked: (photos:Photos)->Unit):RecyclerView.Adapter<PhotoGridAdapter.PhotoGridViewHolder>(){
    private var oldList: List<Photos> = photoList.map { it.copy() }
    private lateinit var newList: List<Photos>

    class PhotoGridViewHolder(private val binding: GridImagesBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(photos: Photos) {
            binding.imgView.setImageResource(photos.image)
            binding.tvName.text = photos.description
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoGridViewHolder {
        return PhotoGridViewHolder(GridImagesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: PhotoGridViewHolder, position: Int) {
        holder.bind(photoList[position])
       holder.itemView.setOnClickListener {
           clicked(photoList[position])
       }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun filter(queryTxt: String?) {
        queryTxt?.let {
            newList = oldList.filter { it.description.contains(queryTxt, ignoreCase = true) }.map { it.copy() }
            photoList = newList
            notifyDataSetChanged()
        }?:run{
            photoList = oldList
        }
    }

}


