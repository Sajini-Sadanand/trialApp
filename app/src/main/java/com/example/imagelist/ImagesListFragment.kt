package com.example.imagelist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imagelist.databinding.FragmentImagesListBinding
import com.example.imagelist.viewmodel.ListViewModel
import com.example.imagelist.viewmodel.Photos

class ImagesListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var binding: FragmentImagesListBinding
    val sharedViewModel:ListViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentImagesListBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.photos.observe(viewLifecycleOwner,{
            binding.rvView.adapter = PhotoGridAdapter(it, clicked =this@ImagesListFragment::onItemClicked)
        })
        sharedViewModel.queryTxt.observe(viewLifecycleOwner,{
            (binding.rvView.adapter as PhotoGridAdapter).filter(it)
        })

    }

    fun onItemClicked(photos: Photos){
        val bundle = Bundle()
        bundle.putInt("Image",photos.image)
        bundle.putString("Dec",photos.description)
        sharedViewModel.setQueryText("")
        findNavController().navigate(R.id.action_imagesListFragment_to_detailFragment,bundle)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        // Inflate the options menu from XML
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)

        // Get the SearchView and set the searchable configuration
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            // Assumes current activity is the searchable activity
            setSearchableInfo(searchManager.getSearchableInfo(activity?.componentName))
            isIconifiedByDefault = false // Do not iconify the widget; expand it by default
            setOnQueryTextListener(this@ImagesListFragment)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        sharedViewModel.setQueryText(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        sharedViewModel.setQueryText(newText)
        return true
    }
}