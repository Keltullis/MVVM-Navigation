package com.bignerdranch.android.mvvmnavigation.screens.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bignerdranch.android.mvvmnavigation.databinding.FragmentEditBinding
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseFragment
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseScreen
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseViewModel
import com.bignerdranch.android.mvvmnavigation.screens.base.screenViewModel

class EditFragment:BaseFragment() {

    class Screen(val initialValue:String):BaseScreen

    override val viewModel by screenViewModel<EditViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val binding = FragmentEditBinding.inflate(inflater,container,false)

        viewModel.initialMessageEvent.observe(viewLifecycleOwner){
            it.getValue()?.let { message -> binding.valueEditText.setText(message) }
        }

        binding.saveButton.setOnClickListener {
            viewModel.onSavePressed(binding.valueEditText.text.toString())
        }
        binding.cancelButton.setOnClickListener {
            viewModel.onCancelPressed()
        }
        return binding.root
    }
}