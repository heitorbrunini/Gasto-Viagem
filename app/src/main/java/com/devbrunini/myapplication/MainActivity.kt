package com.devbrunini.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.devbrunini.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //variável é lateinit, não pode ser instanciada ainda: tela ainda não foi chamada em onCreate
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCalculate.setOnClickListener(this)

    }

    private fun isValid(): Boolean{
        return(
                binding.editDistance.text.toString() !=""
                && binding.editPrice.text.toString() !=""
                && binding.editAuto.text.toString() !=""
                && binding.editAuto.text.toString().toFloat() !=0f
                )
    }

    private fun calculate(){

        if (isValid()){

            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAuto.text.toString().toFloat()

            val totalValue = (distance * price)/autonomy
            val totalStrValue = "R$ ${"%.2f".format(totalValue)}"

            binding.viewResult.text = totalStrValue

        } else{
            Toast.makeText(this,R.string.fill_all_fields , Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(v: View) {

        if (v.id == R.id.button_calculate){
            calculate()
        }

    }
}