package com.example.listadecompras

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import	android.app.Activity
import android.content.Intent
import	android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.widget.AdapterView
import	android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CadastroActivity : AppCompatActivity() {

    val	COD_IMAGE	=	101
    var	imageBitMap:	Bitmap?	=	null

    fun	abrirGaleria(){
        val	intent	=	Intent(Intent.ACTION_GET_CONTENT)
        intent.type	=	"image/*"
        startActivityForResult(Intent.createChooser(intent,	"Selecione uma imagem"), COD_IMAGE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cadastro)

        val btn_inserir = findViewById<Button>(R.id.btn_inserir)
        val txt_produto = findViewById<EditText>(R.id.txt_produto)
        val txt_qtd = findViewById<EditText>(R.id.txt_qtd)
        val txt_valor = findViewById<EditText>(R.id.txt_valor)
        val img_foto_produto = findViewById<ImageView>(R.id.img_foto_produto)

        btn_inserir.setOnClickListener {

            val	produto	=	txt_produto.text.toString()
            val	qtd	= txt_qtd.text.toString()
            val	valor = txt_valor.text.toString()

            if(produto.isNotEmpty()	&& qtd.isNotEmpty() && valor.isNotEmpty()) {
                val	prod = Produto(produto,	qtd.toInt(),	valor.toDouble(), imageBitMap)
                Utils.produtosGlobal.add(prod)
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            } else {

                txt_produto.error = if(txt_produto.text.isEmpty()) "Preencha o nome	do produto"	else null
                txt_qtd.error =	if(txt_qtd.text.isEmpty()) "Preencha a quantidade" else	null
                txt_valor.error	= if (txt_valor.text.isEmpty()) "Preencha o	valor" else	null
            }
        }

        img_foto_produto.setOnClickListener	{
            abrirGaleria()
        }
    }

    override fun onActivityResult(requestCode: Int,	resultCode:	Int, data: Intent?)	{
        super.onActivityResult(requestCode,	resultCode,	data)

        val img_foto_produto = findViewById<ImageView>(R.id.img_foto_produto)

        if(requestCode == COD_IMAGE	&& resultCode == Activity.RESULT_OK) {
            if	(data	!=	null)	{
                val	inputStream	= data.getData()?.let { contentResolver.openInputStream(it) };
                imageBitMap	=	BitmapFactory.decodeStream(inputStream)
                img_foto_produto.setImageBitmap(imageBitMap)
            }
        }
    }
}