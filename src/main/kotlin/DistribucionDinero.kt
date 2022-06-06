abstract class DistribucionDinero {

    abstract fun distribuir(listaProyecto: MutableList<Proyecto>, mangos: Double)

}

class ParteIGual : DistribucionDinero() {
    override fun distribuir(listaProyecto: MutableList<Proyecto>, mangos: Double) {
        val cantidadProyectos = listaProyecto.size
        listaProyecto.forEach { it.dineroADisposicion += mangos / cantidadProyectos }
    }
}

class MasAlPrimero : DistribucionDinero() {

    override fun distribuir(listaProyecto: MutableList<Proyecto>, mangos: Double) {
        listaProyecto.first().dineroADisposicion + mangos * 0.5
        listaProyecto.removeFirst()
        val cantidadProyectos = listaProyecto.size
        val mangosRestantes = mangos * 0.5
        listaProyecto.forEach { it.dineroADisposicion += mangosRestantes / cantidadProyectos }

    }
}

class AlAzar : DistribucionDinero() {
    override fun distribuir(listaProyecto: MutableList<Proyecto>, mangos: Double) {
        if(mangos < 1000) throw RuntimeException("Tiene que asignar por lo menos 1000 mangos para realizar la accion")
        listaProyecto.random().dineroADisposicion += 500
        listaProyecto.random().dineroADisposicion += mangos - 500

    }
}