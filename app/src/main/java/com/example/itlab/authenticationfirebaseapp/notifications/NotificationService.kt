package com.example.itlab.authenticationfirebaseapp.notifications

class NotificationService () {
    //Declaramos la variable que instancia la clase NotificationManager.

    //Implementando evento onNewToken para obtener nuevo token.
    fun onNewToken(token: String) {
        //LLamando a método súper de onNewToken.

        //Obtener nuevo token.
    }

    //Implementando evento onMessageReceived para recibir mensajes.
    fun onMessageReceived(remoteMessage: Any) {
        //Obteniendo el servicio de notificaciones del sistema Android como administrador de notificaciones.

        //Generando ID para cada notificación que se va a agregar al centro de notificaciones de Android.

        //Recibiendo título del mensaje.

        //Recibiendo cuerpo del mensaje.

        //Obtener sonido por defecto para reproducirlo cuando llegue una nueva notificación.

        //Construyendo nueva notificación.

        //Agregando notificación al centro de notificaciones.
    }

}