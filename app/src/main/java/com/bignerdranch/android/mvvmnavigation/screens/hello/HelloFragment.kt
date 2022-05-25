package com.bignerdranch.android.mvvmnavigation.screens.hello

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.mvvmnavigation.databinding.FragmentHelloBinding
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseFragment
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseScreen
import com.bignerdranch.android.mvvmnavigation.screens.base.screenViewModel

class HelloFragment:BaseFragment() {

    class Screen: BaseScreen

    override val viewModel by screenViewModel<HelloViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHelloBinding.inflate(inflater,container,false)

        binding.editButton.setOnClickListener {
            viewModel.onEditPressed()
        }

        viewModel.currentMessageLiveData.observe(viewLifecycleOwner){
            binding.valueTextView.text = it
        }

        return binding.root
    }
}

// Всё что делает фрагмент,это передаёт управление вью модели на слушателях
// а так же слушает лив даты из вью модели,всем управляет вью модель