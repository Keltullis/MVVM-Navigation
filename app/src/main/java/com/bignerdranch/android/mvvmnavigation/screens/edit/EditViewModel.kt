package com.bignerdranch.android.mvvmnavigation.screens.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.mvvmnavigation.Event
import com.bignerdranch.android.mvvmnavigation.R
import com.bignerdranch.android.mvvmnavigation.navigator.Navigator
import com.bignerdranch.android.mvvmnavigation.screens.base.BaseViewModel
import com.bignerdranch.android.mvvmnavigation.screens.edit.EditFragment.Screen

class EditViewModel(
    private val navigator: Navigator,
    screen:Screen
):BaseViewModel() {

    private val _initialMessageEvent = MutableLiveData<Event<String>>()
    val initialMessageEvent:LiveData<Event<String>> = _initialMessageEvent

    init {
        _initialMessageEvent.value = Event(screen.initialValue)
    }

    fun onSavePressed(message:String){
        if (message.isBlank()){
            navigator.toast(R.string.empty_message)
            return
        }
        navigator.goBack(message)
    }

    fun onCancelPressed(){
        navigator.goBack()
    }

}