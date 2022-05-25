package com.bignerdranch.android.mvvmnavigation.navigator

import com.bignerdranch.android.mvvmnavigation.MainActivity

// Вспомогательный класс,он необходим что бы косвенно ссылатся на активити,а это нужно что бы реализовать запуск фрагментов и возврат

typealias MainActivityAction = (MainActivity) -> Unit

class MainActivityActions {

    var mainActivity:MainActivity? = null
        set(activity){
            field = activity
            if(activity!= null){
                actions.forEach { it(activity) }
                actions.clear()
            }
        }

    private val actions = mutableListOf<MainActivityAction>()

    operator fun invoke(action:MainActivityAction){
        val activity = this.mainActivity
        if(activity == null){
            actions += action
        } else{
            action(activity)
        }
    }

    fun clear(){
        actions.clear()
    }

}