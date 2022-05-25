package com.bignerdranch.android.mvvmnavigation.screens.base

import androidx.lifecycle.ViewModel


// Базовый класс для всех вью моделей
open class BaseViewModel :ViewModel(){

    //сюда будут приходить результаты из других экранов
    open fun onResult(result:Any){

    }
}