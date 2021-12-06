package com.example.ets7100

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _paikkakunta = MutableLiveData("Paikkakunta...")
    val paikkakunta: LiveData<String> = _paikkakunta

    fun tallennaPaikkakunta(newPaikkakunta: String) {
        _paikkakunta.value = newPaikkakunta
    }
}