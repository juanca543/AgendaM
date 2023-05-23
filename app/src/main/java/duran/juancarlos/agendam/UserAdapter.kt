package duran.juancarlos.agendam

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var list= mutableListOf<User>()
    private var actionDelete: ((User) -> Unit)?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.card_item_view_holder, parent, false)

        return UserViewHolder(view)

    }

    override fun getItemCount()= list.size

    fun setData(data: List<User>){

        list.apply{
            clear()
            addAll(data)
        }

        notifyDataSetChanged()

    }


    fun setOnActionDeleteLinester(callback: (User)-> Unit){

        this.actionDelete= callback
    }


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

        val user= list[position]

        holder.tvname.text=user.nombre
        holder.tvtelefono.text=user.telefono
        holder.tvconsulta.text=user.consulta
        holder.tvfecha.text=user.fecha
        holder.tvhora.text=user.hora

        holder.actionDelete.setOnClickListener{ actionDelete?.invoke(user)}
    }

    class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val tvname=itemView.findViewById<TextView>(R.id.name)
        val tvtelefono=itemView.findViewById<TextView>(R.id.telefono)
        val tvconsulta=itemView.findViewById<TextView>(R.id.consulta)
        val tvfecha=itemView.findViewById<TextView>(R.id.fecha)
        val tvhora=itemView.findViewById<TextView>(R.id.hora)
        val actionDelete: ImageView = itemView.findViewById(R.id.action_delete)

    }
}