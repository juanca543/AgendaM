package duran.juancarlos.agendam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import duran.juancarlos.agendam.databinding.ActivityMenuBinding
import kotlinx.coroutines.launch

class MenuActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMenuBinding
    private var user: User?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (user == null) binding.btnRegistro.text="Registrar"
        else {
            binding.btnRegistro.text = "Update"
            binding.firstName.setText(user?.nombre.toString())
            binding.phoneNumber.setText(user?.telefono.toString())
            binding.textConsulta.setText(user?.consulta.toString())
            binding.textFecha.setText(user?.fecha.toString())
            binding.textHora.setText(user?.hora.toString())
        }

        val inicio = findViewById<TextView>(R.id.goToInicio)
        inicio.setOnClickListener {

            gotoinicio()
        }


        binding.btnRegistro.setOnClickListener { view: View ->

            insert()

            Toast.makeText(
                this,
                R.string.Registro_Correcto,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun gotoinicio(){

        val i= Intent(this,MainActivity::class.java)
        startActivity(i)
    }

    private fun insert(){

        val nombre= binding.firstName.text.toString()
        val telefono= binding.phoneNumber.text.toString()
        val consulta= binding.textConsulta.text.toString()
        val fecha= binding.textFecha.text.toString()
        val hora=binding.textHora.text.toString()


        lifecycleScope.launch {
            if (user == null){

                val persona= User(nombre = nombre, telefono = telefono, consulta = consulta,
                    fecha= fecha, hora = hora)

                AppDatabase(this@MenuActivity).getUserDao().insert(persona)
                finish()
            }else {

                    val u= User(nombre, telefono, consulta, fecha, hora)
                    u.id= user?.id ?: 0
                AppDatabase(this@MenuActivity).getUserDao().update(u)
                finish()
            }

        }

    }

}