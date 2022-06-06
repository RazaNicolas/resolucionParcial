import java.time.LocalDate
import java.time.Period


class Proyecto(
    val nombre: String,
    val descripcion: String,
    val plataRequerida: Int,
    val datoBancario: Int,
    val personasResponsables: MutableMap<String, String>,
    val origen: String,
    val tipo: TipoProyecto,
    //val fechaDeInicio : LocalDate = LocalDate.now() // mandar una fecha a pedal LocalDate.of(2018, 10, 30)
) {
    fun impactoSocial(): Double {
        return plataRequerida * 0.1 + tipo.ejecutar(this)
    }
}

class Usuario(
    val nombre: String
) {

    fun seleccionarProyectos() {

    }
}

interface SeleccionProyecto {

    fun elegirProyectos()
}

class ImpactoSocial : SeleccionProyecto {
    override fun elegirProyectos() {

    }


}

class DineroRequerido : SeleccionProyecto {
    override fun elegirProyectos() {
        TODO("Not yet implemented")
    }

}

class Nacionales : SeleccionProyecto {
    override fun elegirProyectos() {
        TODO("Not yet implemented")
    }

}

class proyectoCombinado(val condiciones: MutableList<SeleccionProyecto>) : SeleccionProyecto {

    override fun elegirProyectos() {

    }

}

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
