package com.alandvg.mcontigotest.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable

import com.alandvg.mcontigotest.R
import com.alandvg.mcontigotest.databinding.SearchFragmentBinding
import com.alandvg.mcontigotest.util.KeyboardUtil
import com.alandvg.mcontigotest.viewmodel.SearchViewModel
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.view.*
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer




class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel
    private lateinit var binding: SearchFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        viewModel.enabledSearch.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel.enabledSearch.get()) {
                    binding.editTextSearch.requestFocus()
                    KeyboardUtil.showSoftKeyboard(binding.editTextSearch)
                } else {
                    KeyboardUtil.hideSoftKeyboard(binding.editTextSearch)
                    binding.editTextSearch.clearFocus()
                }
            }
        })

        viewModel.linkSelected.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(!viewModel.linkSelected.get().isNullOrEmpty()){

                    try {
                        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(viewModel.linkSelected.get()))
                        startActivity(myIntent)
                    } catch (e: ActivityNotFoundException) {
                        e.printStackTrace()
                    }

                    viewModel.linkSelected.set("")
                }
            }

        })

        viewModel.previewSelected.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback(){
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if(!viewModel.previewSelected.get().isNullOrEmpty()){

                    val player = ExoPlayerFactory.newSimpleInstance(context)

                    viewModel.previewSelected.set("")
                }
            }

        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        binding.viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        binding.editTextSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.enabledSearch.set(false)
            }

            false
        }


    }


}
