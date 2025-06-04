package com.example.listadecompras

import	android.graphics.Bitmap
import com.example.listadecompras.data.ItemEntity
import com.example.listadecompras.data.toModel

data class Produto(val id: Long, val nome:String,	val quantidade:Int,	val	valor:Double , val foto: Bitmap? = null, val onRemove: ((Produto) -> Unit)? = null)

fun Produto.toEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        nome = this.nome,
        quantidade = this.quantidade,
        valor = this.valor
    )
}