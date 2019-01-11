package com.alandvg.mcontigotest.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.alandvg.mcontigotest.R
import com.alandvg.mcontigotest.databinding.MainHostActivityBinding




class MainActivity : AppCompatActivity() {


    lateinit var binding : MainHostActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.main_host_activity)

    }


}

