package dev.mlds.walletrequest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dev.mlds.walletrequest.R
import dev.mlds.walletrequest.di.ConfRetrofit
import dev.mlds.walletrequest.model.Card
import dev.mlds.walletrequest.service.ServiceCard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.UUID

class CadastroActivity : AppCompatActivity() {

    private lateinit var configuracao: ConfRetrofit
    private lateinit var servico: ServiceCard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cardastro)

        configuracao = ConfRetrofit()
        servico = configuracao.service

        //usar para procurar a lista de cartoes que foram cadastrados
        var call = servico.getAll()
        call.enqueue(object : Callback<List<Card>> {
            override fun onResponse(call: Call<List<Card>>, response: Response<List<Card>>) {

                response.body()?.let { cards ->
                    for (card in cards) {
                        println(card.name)
                    }
                }
            }

            override fun onFailure(call: Call<List<Card>>, t: Throwable) {

            }

        })

        val edName = findViewById<EditText>(R.id.edName)
        val edNumber = findViewById<EditText>(R.id.edNumber)
        val edVencimento = findViewById<EditText>(R.id.edVencimento)
        val edCode = findViewById<EditText>(R.id.edCode)


        val btAvancar = findViewById<Button>(R.id.btAvancar)
        btAvancar.setOnClickListener {

            val id: String = UUID.randomUUID().toString()
            val type = "BLACK"
            val name = edName.text
            val number = edNumber.text
            val vencimento = edVencimento.text
            val code = edCode.text

            val newCard = Card(
                id = id,
                name = name.toString(),
                cvv = code.toString(),
                number = number.toString(),
                expirationDate = vencimento.toString(),
                cardType = type
            )

            // habilitar o botão avançar da tela de cadastro

            edName.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    verificaCampos()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            edNumber.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    verificaCampos()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            edVencimento.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    verificaCampos()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            edCode.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    verificaCampos()
                }

                override fun afterTextChanged(s: Editable?) {
                }
            })

            val btAvancar = findViewById<Button>(R.id.btAvancar)
            btAvancar.setOnClickListener {

                val id: String = UUID.randomUUID().toString()
                val type = "BLACK"
                val name = edName.text
                val number = edNumber.text
                val vencimento = edVencimento.text
                val code = edCode.text

                val newCard = Card(
                    id = id,
                    name = name.toString(),
                    cvv = code.toString(),
                    number = number.toString(),
                    expirationDate = vencimento.toString(),
                    cardType = type
                )

                executarRequest(newCard)
            }
        }


        executarRequest(newCard)
        }
    }


//informar que dados foram cadastrados

private fun executarRequest(newCard: Card) {
    servico.createCard(newCard).enqueue(object : Callback<Card> {
        override fun onResponse(call: Call<Card>, response: Response<Card>) {
            Toast.makeText(this@CadastroActivity, "Sucesso! Dados cadastrados com sucesso!", Toast.LENGTH_LONG).show()
        }

        override fun onFailure(call: Call<Card>, t: Throwable) {
            Log.e("FATAL", t.message.orEmpty())
            Toast.makeText(this@CadastroActivity, "Erro! Falha de comunicação!", Toast.LENGTH_LONG).show()
        }

    })
}

    private fun executarRequest(newCard: Card) {
        servico.createCard(newCard).enqueue(object : Callback<Card> {
            override fun onResponse(call: Call<Card>, response: Response<Card>) {
                Toast.makeText(
                    this@CadastroActivity,
                    "Sucesso! Dados cadastrados com sucesso!",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onFailure(call: Call<Card>, t: Throwable) {
                Log.e("FATAL", t.message.orEmpty())
                Toast.makeText(
                    this@CadastroActivity,
                    "Erro! Falha de comunicação!",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
    }
private fun verificaCampos() {
    val edName = findViewById<EditText>(R.id.edName)
    val edNumber = findViewById<EditText>(R.id.edNumber)
    val edVencimento = findViewById<EditText>(R.id.edVencimento)
    val edCode = findViewById<EditText>(R.id.edCode)

    val btAvancar = findViewById<Button>(R.id.btAvancar)
    btAvancar.isEnabled = edName.text.isNotEmpty() && edNumber.text.isNotEmpty() && edVencimento.text.isNotEmpty() && edCode.text.isNotEmpty()
}
}
