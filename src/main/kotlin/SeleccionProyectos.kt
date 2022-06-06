abstract class SeleccionProyecto() {
    var listaProyectoElegidos : MutableList<Proyecto> = mutableListOf()

    abstract fun elegirProyectos(listaProyecto: List<Proyecto>) : List<Proyecto>

    fun proyectosElegibles() = if(listaProyectoElegidos.size > 1) listaProyectoElegidos else throw RuntimeException("No tiene por lo menos 2 proyectos elegibles")
}

class ImpactoSocial : SeleccionProyecto() {

    override fun elegirProyectos(listaProyecto: List<Proyecto>)  : List<Proyecto>{
        proyectosConMasImpactoSocial(listaProyecto)
        return proyectosElegibles()
    }

    fun proyectosConMasImpactoSocial(listaProyecto: List<Proyecto>){
        listaProyectoElegidos.add( listaProyecto.maxByOrNull { it.impactoSocial() }!!)

    }

}

class DineroRequerido : SeleccionProyecto() {

    override fun elegirProyectos(listaProyecto: List<Proyecto>): List<Proyecto> {
        proyectoMasCaro(listaProyecto)
        proyectoMasBarato(listaProyecto)
        return proyectosElegibles()
    }

    fun proyectoMasCaro(listaProyecto: List<Proyecto>) = listaProyectoElegidos.add(listaProyecto.maxByOrNull { it.plataRequerida }!!)
    fun proyectoMasBarato(listaProyecto: List<Proyecto>) = listaProyectoElegidos.add(listaProyecto.minByOrNull { it.plataRequerida }!!)




}

class Nacionales : SeleccionProyecto() {
    override fun elegirProyectos(listaProyecto: List<Proyecto>): List<Proyecto> {
        listaProyectoElegidos.addAll( listaProyecto.filter { it.origen == "Nacional" })
        return proyectosElegibles()

    }

}

class proyectoCombinado(val condiciones: List<SeleccionProyecto>) : SeleccionProyecto() {

    override fun elegirProyectos(listaProyecto: List<Proyecto>): List<Proyecto> {
        listaProyectoElegidos.addAll( condiciones.flatMap { it.elegirProyectos(listaProyecto) })
        return proyectosElegibles()

    }

}