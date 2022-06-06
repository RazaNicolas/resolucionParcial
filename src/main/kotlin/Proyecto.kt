class Proyecto(
    val nombre: String,
    val descripcion: String,
    val plataRequerida: Int,
    val datoBancario: Int,
    val personasResponsables: MutableMap<String, String>,
    val origen: String,
    val tipo: TipoProyecto,
    var dineroADisposicion: Double

    //val fechaDeInicio : LocalDate = LocalDate.now() // mandar una fecha a pedal LocalDate.of(2018, 10, 30)
) {
    fun impactoSocial(): Double {
        return plataRequerida * 0.1 + tipo.ejecutar(this)
    }
}

class Usuario(
    val nombre: String,
    val listaProyectos: MutableList<Proyecto>,
    var ideas: SeleccionProyecto,
    val distribucionDinero: DistribucionDinero

) {

    fun seleccionarProyectos(listaProyecto: List<Proyecto>): List<Proyecto> {
        return ideas.elegirProyectos(listaProyecto)
    }

    fun distribuirDinero(listaProyecto: MutableList<Proyecto>, mangos: Double) {
        distribucionDinero.distribuir(listaProyecto,mangos)
    }
}



