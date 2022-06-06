abstract class Proyecto(
    val nombre: String,
    val descripcion: String,
    val plataRequerida: Int,
    val datoBancario: String,
    val personasResponsables: MutableMap<String, String>,
    val origen: String,
    val tipo: TipoProyecto,
    var dineroADisposicion: Double
    //val fechaDeInicio : LocalDate = LocalDate.now() // mandar una fecha a pedal LocalDate.of(2018, 10, 30)
) {
    var cantidadDonaciones = 0
    var depositoInmediato: Boolean = true
    var activo = true

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
    var numero: Int = 0
    var mangos : Double = 0.0

    fun seleccionarProyectos(listaProyecto: MutableList<Proyecto>): MutableList<Proyecto> {
        return ideas.elegirProyectos(listaProyecto)
    }

    fun distribuirDinero(listaProyecto: MutableList<Proyecto>, mangos: Double) {
        distribucionDinero.distribuir(listaProyecto, mangos)
    }
}


class SistemaBancario(val proyecto: Proyecto, val inversor: Usuario) {
    lateinit var mailSender: MailSender
    lateinit var whatsappSender: WhatsappSender

    fun transferir(cuentaOrigenId: String, cuentaDestinoId: String, montoEntero: Int, montoDecimales: Int): String {
        val montoTotal = montoEntero + montoDecimales / 100
        superaCantidadDeDonaciones()
        if (!proyecto.activo) throw java.lang.RuntimeException("No puede recibir donaciones supero el limite")
        if (montoEntero > 2500) enviarWhatsapp(montoTotal)
        enviarMail()
        proyecto.cantidadDonaciones += 1
        proyecto.dineroADisposicion += montoTotal
        inversor.mangos -= montoTotal
        return " "
    }

    fun enviarWhatsapp(montoEntero: Int) {
        whatsappSender.enviarWhatsapp(
            Whatsapp(
                to = inversor.numero, subject = "El monto inverto es $montoEntero del proyecto : $proyecto "
            )
        )
    }


    fun enviarMail() {
        proyecto.personasResponsables.forEach { persona ->
            mailSender.enviarMail(
                Mail(
                    to = persona.value, subject = "Transferencia realizada con exito"
                )
            )
        }
    }

    fun superaCantidadDeDonaciones() {
        if (proyecto.cantidadDonaciones >= 3) proyecto.activo = false
    }


}
