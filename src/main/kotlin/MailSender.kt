interface MailSender {

    fun enviarMail(mail: Mail)

}

data class Mail(val to: String, val subject: String)

interface WhatsappSender {

    fun enviarWhatsapp(whatsapp: Whatsapp)

}

data class Whatsapp(val to: Int, val subject: String)