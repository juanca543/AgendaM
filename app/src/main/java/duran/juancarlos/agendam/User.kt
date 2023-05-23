package duran.juancarlos.agendam

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName= "user")

data class User (

    var nombre: String="",
    var telefono: String="",
    var consulta:String="",
    var fecha:String="",
    var hora: String=""

        ):Serializable {

    @PrimaryKey(autoGenerate= true) var id :Int=0
}


