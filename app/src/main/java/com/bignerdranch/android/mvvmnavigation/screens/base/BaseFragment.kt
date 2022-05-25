package com.bignerdranch.android.mvvmnavigation.screens.base

import androidx.fragment.app.Fragment

// От этого класса будут наследоваться все фрагменты
// здесь же хранится вью модель
abstract class BaseFragment:Fragment() {

    abstract val viewModel:BaseViewModel

}