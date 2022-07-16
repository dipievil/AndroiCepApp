package br.dipievil.appcep

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {

    lateinit var txtCep : EditText
    lateinit var txtResponse : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtCep =findViewById(R.id.txtCep);
        txtResponse = findViewById(R.id.txtResponse);

        getData()
    }

    fun getData() {
        val retrofitClient = NetworkUtils
            .getRetrofitInstance("https://viacep.com.br/")

        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Product>> {
            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                response.body()?.forEach {

                    txtResponse.text = txtResponse.text.toString().plus(it.toString())
                }
            }
        })
    }
}

interface Endpoint {
    @GET("ws/91740820/json/")
    fun getPosts() : Call<List<Product>>
}

class Product {
    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("preco")
    @Expose
    var preco: Int? = null
}

class ViaCep {
    @SerializedName("cep")
    @Expose
    var cep: String? = null

    @SerializedName("logradouro")
    @Expose
    var logradouro: String? = null

    @SerializedName("localidade")
    @Expose
    var localidade: String? = null
}
