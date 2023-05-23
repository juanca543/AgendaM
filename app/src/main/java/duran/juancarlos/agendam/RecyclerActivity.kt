package duran.juancarlos.agendam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import duran.juancarlos.agendam.databinding.ActivityRecyclerBinding
import kotlinx.coroutines.launch

class RecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerBinding
    private var mAdapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun setAdapter(list: List<User>){

        mAdapter?.setData(list)

    }

    override fun onResume() {

        super.onResume()
        lifecycleScope.launch {
            val userList = AppDatabase(this@RecyclerActivity).getUserDao().getAllUser()

            mAdapter = UserAdapter()
            binding.recyclerView.apply {
                layoutManager = LinearLayoutManager(this@RecyclerActivity)
                adapter = mAdapter
                    setAdapter(userList)


                    mAdapter?.setOnActionDeleteLinester {
                        val builder = AlertDialog.Builder(this@RecyclerActivity)
                        builder.setMessage("¿Estas seguro de eliminarlo?")
                        builder.setPositiveButton("SI") { p0, p1 ->
                            lifecycleScope.launch {
                                AppDatabase(this@RecyclerActivity).getUserDao().delete(it)
                                val list=AppDatabase(this@RecyclerActivity).getUserDao().getAllUser()
                                    setAdapter(list)
                            }

                            p0.dismiss()

                        }

                        builder.setNegativeButton("NO") { p0, p1 ->
                            p0.dismiss()
                        }
                        val dialog = builder.create()
                        dialog.show()

                    }

            }
        }

    }
}