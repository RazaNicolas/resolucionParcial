import java.time.LocalDate
import java.time.Period

interface TipoProyecto {

    fun ejecutar(proyecto: Proyecto): Double
}

class social(val fechaDeInicio: LocalDate) : TipoProyecto {
    override fun ejecutar(proyecto: Proyecto): Double {
        return maxOf((Period.between(fechaDeInicio, LocalDate.now()).years) - 1.0, 1.0) * 100.0
    }
}

class cooperativa(val listaSocios: MutableList<Usuario>) : TipoProyecto {
    val sociosConUnApellido = listaSocios.count { it.nombre.contains(" ") }
    val sociosConDosApellido = listaSocios.size - sociosConUnApellido
    override fun ejecutar(proyecto: Proyecto): Double {
        return sociosConUnApellido * 30.0 + sociosConDosApellido * 45.0
    }
}

class ecologico(val metrosCuadrados: Int) : TipoProyecto {
    override fun ejecutar(proyecto: Proyecto): Double {
        return metrosCuadrados * 10.0
    }
}