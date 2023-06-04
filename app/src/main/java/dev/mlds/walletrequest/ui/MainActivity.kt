package dev.mlds.walletrequest.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import dev.mlds.walletrequest.di.ConfRetrofit
import dev.mlds.walletrequest.R
import retrofit2.Call
import retrofit2.Callback
import dev.mlds.walletrequest.model.Card
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val openCard = findViewById<Button>(R.id.btOpenCad)
        openCard.setOnClickListener {

            val intentCad = Intent(this, CadastroActivity::class.java)
            startActivity(intentCad)

        }

    }
}