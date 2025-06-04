package com.example.listadecompras

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ListView
import	java.text.NumberFormat
import	java.util.Locale
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import	android.widget.ArrayAdapter
import android.widget.EditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val list_view_produtos = findViewById<ListView>(R.id.list_view_produtos)
        val	produtosAdapter	=	ProdutoAdapter(this)
        produtosAdapter.addAll(Utils.produtosGlobal)
        val btn_adicionar = findViewById<Button>(R.id.btn_adicionar)

//        val viewModel: ItemsViewModel by viewModels {
//            ItemsViewModelFactory(applicationContext)
//        }

        btn_adicionar.setOnClickListener	{
            //Criando	a	Intent	explícita
            val	intent = Intent(this,	CadastroActivity::class.java)
            //iniciando	a atividade
            startActivity(intent)
        }

        list_view_produtos.adapter	=	produtosAdapter

        list_view_produtos.setOnItemLongClickListener{ adaptiveView: AdapterView<*>, view: View, position: Int, id: Long ->
            val item = produtosAdapter.getItem(position);
            produtosAdapter.remove(item);
            true;
        }

    }
    override fun onResume()	{
        super.onResume()

        val list_view_produtos = findViewById<ListView>(R.id.list_view_produtos)
        val	adapter	= list_view_produtos.adapter as ProdutoAdapter
        val txt_total = findViewById<TextView>(R.id.txt_total)

        adapter.clear()
        adapter.addAll(Utils.produtosGlobal)

        val	soma	= Utils.produtosGlobal.sumOf { it.valor * it.quantidade }
        val	f =	NumberFormat.getCurrencyInstance(Locale("pt",	"br"))
        txt_total.text	=	"TOTAL:	${	f.format(soma)}"
    }
}