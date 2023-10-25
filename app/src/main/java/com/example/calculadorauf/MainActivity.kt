package com.example.calculadorauf
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.calculadorauf.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var calcularButton: Button
    lateinit var resultado : String
    lateinit var fechaSpinner: DatePicker
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fechaSpinner = findViewById<DatePicker>(R.id.fechaSpinner)
        calcularButton = findViewById(R.id.calcularButton)
        binding.fechaConsulta.text = fechaActual()
        calcularButton.setOnClickListener {
            actualizarUf(fechaSpinner)
        }


    }
    fun actualizarUf(fechaSpinner : DatePicker ){
        val retrofitTraer = RetrofitClient.consumirApi.getUf(getFechaSpinner(fechaSpinner))
        retrofitTraer.enqueue(object : Callback<Json> {
            override fun onResponse(call: Call<Json>, response: Response<Json>){
                var resultado = response.body()?.serie?.map {it.valor}.toString()
                resultado = resultado.replace("[","")
                resultado = resultado.replace("]","")
                var entero = 10
                binding.valorUF.text = resultado
                binding.calculoTotal.setText(entero)
            }
            override fun onFailure(Call: Call<Json>, t: Throwable){
                Toast.makeText(this@MainActivity,"Error al consultar Api Rest", Toast.LENGTH_LONG).show()
            }
        })
    }
    fun fechaActual(): String{
        val calendar = Calendar.getInstance().time
        return DateFormat.getDateInstance().format(calendar)
    }
    private fun getFechaSpinner(fecha:DatePicker):String{
        var day =fecha.dayOfMonth.toString()
        var month = fecha.month.toString()
        var year = fecha.year.toString()
        return "$day-$month-$year"
    }
    

}