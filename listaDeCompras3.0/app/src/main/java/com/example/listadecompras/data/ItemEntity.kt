package com.example.listadecompras.data

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.listadecompras.Produto

@Entity
data class ItemEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val nome: String,
    val quantidade: Int,
    val valor: Double
)

fun ItemEntity.toModel(onRemove: (Produto) -> Unit): Produto {
    return Produto(
        id = this.id,
        nome = this.nome,
        quantidade = this.quantidade,
        valor = this.valor,
        onRemove = onRemove
    )
}
