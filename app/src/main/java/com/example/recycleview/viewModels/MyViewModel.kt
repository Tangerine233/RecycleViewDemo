package com.example.recycleview.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recycleview.data.DummyData

class MyViewModel: ViewModel() {
    private val _dataList = MutableLiveData<List<DummyData>>()
    val dataList: LiveData<List<DummyData>> get() = _dataList

    init {
        // Initialize _dataList with dummy data
        for (i in 0..25){
            addItem(DummyData("Title $i", "Description $i", false))
        }
    }

    private fun addItem(item: DummyData) {
        val currentList = _dataList.value ?: emptyList()
        val newList = currentList.toMutableList()
        newList.add(item)
        _dataList.value = newList

    }

    fun toggleListener(position: Int){
        val currentList = _dataList.value ?: emptyList()
        val newList = currentList.toMutableList()
        newList[position].listening = !newList[position].listening
        _dataList.value = newList
    }

    fun clickButton(){
        val size = _dataList.value?.size ?: 0
        addItem(DummyData("Title $size", "Description $size", false))
    }
}