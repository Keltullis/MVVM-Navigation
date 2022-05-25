package com.bignerdranch.android.mvvmnavigation.screens.hello

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.mvvmnavigation.R
import com.bignerdranch.android.mvvmnavigation.navigator.Navigator
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseViewModel
import com.bignerdranch.android.mvvmnavigation.screens.edit.EditFragment

class HelloViewModel(
    private val navigator: Navigator,
    screen: HelloFragment.Screen
):BaseViewModel() {
    private val _currentMessageLiveData = MutableLiveData<String>()
    val currentMessageLiveData:LiveData<String> = _currentMessageLiveData

    init {
        _currentMessageLiveData.value = navigator.getString(R.string.hello_world)
    }

    override fun onResult(result: Any) {
        if(result is String){
            _currentMessageLiveData.value = result
        }
    }

    // Метод берёт текущее значение лайвдаты и запускает новый экран с этим значением
    fun onEditPressed(){
        navigator.launch(EditFragment.Screen(initialValue = currentMessageLiveData.value!!))
    }
}