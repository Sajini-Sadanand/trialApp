package com.example.imagelist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.imagelist.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {


    private lateinit var dec: String
    private var image:Int = 0
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater,container,false)
        dec = arguments?.get("Dec").toString()
        image = arguments?.get("Image") as Int
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imgDetails.setImageResource(image)
        binding.txtDetails.text = dec
    }
}