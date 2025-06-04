package com.example.listadecompras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.listadecompras.data.ItemsDatabase
import androidx.lifecycle.viewModelScope
import com.example.listadecompras.data.ItemEntity
import com.example.listadecompras.data.toModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers


class ItemsViewModel(

    private val database: ItemsDatabase
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchAll()
        }
    }

    val itemsLiveData = MutableLiveData<List<Produto>>()

    fun addItem(name: String, quantidade: Int, valor: Double) {
        viewModelScope.launch(Dispatchers.IO){
            val entity = ItemEntity(id=0, nome = name, quantidade = quantidade, valor = valor)
            database.itemsDao().insert(entity)
            fetchAll()
        }
    }

    private suspend fun fetchAll() {
        val result = database.itemsDao().getAll().map {
            it.toModel(onRemove = ::removeItem)
        }

        itemsLiveData.postValue(result)
    }

    private fun removeItem(item: Produto) {
        viewModelScope.launch(Dispatchers.IO) {
            val entity = item.toEntity()
            database.itemsDao().delete(entity)
            fetchAll()
        }
    }

}