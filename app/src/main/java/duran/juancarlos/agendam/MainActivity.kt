package duran.juancarlos.agendam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import duran.juancarlos.agendam.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnReservarCita= findViewById<Button>(R.id.btn_reservar_sita)
        btnReservarCita.setOnClickListener{

            goToCreateAppoinment()

        }

        val btnMisCitas= findViewById<Button>(R.id.btn_ver_citas)
        btnMisCitas.setOnClickListener{

            gotoMisCitas()
        }

    }

    private fun gotoMisCitas(){

        val i= Intent(this,RecyclerActivity::class.java)
        startActivity(i)

    }

    private fun goToCreateAppoinment(){
        val i= Intent(this,MenuActivity::class.java)
        startActivity(i)
    }




}