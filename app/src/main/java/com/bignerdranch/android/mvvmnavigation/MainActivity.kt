package com.bignerdranch.android.mvvmnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.bignerdranch.android.mvvmnavigation.R
import com.bignerdranch.android.mvvmnavigation.navigator.MainNavigator
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseFragment
import com.bignerdranch.android.mvvmnavigation.screens.hello.HelloFragment

class MainActivity : AppCompatActivity() {

    private val navigator by viewModels<MainNavigator> { AndroidViewModelFactory(application) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            navigator.launchFragment(this,HelloFragment.Screen(),addToBackStack = false)
        }

        supportFragmentManager.registerFragmentLifecycleCallbacks(fragmentCallbacks,false)
    }

    override fun onDestroy() {
        super.onDestroy()
        supportFragmentManager.unregisterFragmentLifecycleCallbacks(fragmentCallbacks)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    // Если мэйн активити активна,передаём ссылку на неё
    override fun onResume() {
        super.onResume()
        navigator.whenActivityActive.mainActivity = this
    }
    // Если нет,то затираем ссылку,это убережёт от утечек памяти из-за поворота экрана
    override fun onPause() {
        super.onPause()
        navigator.whenActivityActive.mainActivity = null
    }

    private val fragmentCallbacks = object : FragmentManager.FragmentLifecycleCallbacks(){
        override fun onFragmentViewCreated(fm: FragmentManager, f: Fragment, v: View, savedInstanceState: Bundle?) {
            if(supportFragmentManager.backStackEntryCount > 0){
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            }
            val result = navigator.result.value?.getValue() ?: return
            if(f is BaseFragment){
                f.viewModel.onResult(result)
            }
        }
    }
}