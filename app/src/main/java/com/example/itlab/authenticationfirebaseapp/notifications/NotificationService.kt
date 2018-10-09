package com.example.itlab.authenticationfirebaseapp.notifications

import android.os.Build
import android.support.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService : FirebaseMessagingService() {
    //Declaramos la variable que instancia la clase NotificationManager.

    //Implementando evento onNewToken para obtener nuevo token.
    override fun onNewToken(token: String) {
        //LLamando a método súper de onNewToken.

        //Obtener nuevo token.
    }

    //Implementando evento onMessageReceived para recibir mensajes.
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        //Obteniendo el servicio de notificaciones del sistema Android como administrador de notificaciones.

        //LLamando a método de compatiblidad con Android Oreo.

        //Generando ID para cada notificación que se va a agregar al centro de notificaciones de Android.

        //Recibiendo título del mensaje.

        //Recibiendo cuerpo del mensaje.

        //Obtener sonido por defecto para reproducirlo cuando llegue una nueva notificación.

        //Construyendo nueva notificación.

        //Agregando notificación al centro de notificaciones.
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private fun setupChannels() {
        //Obteniendo nombre de canal de notificaciones del archivo strings.xml.

        //Obteniendo descripción de canal de notificaciones del archivo strings.xml.

        //Instanciando canal de notificaciones

        //Generando nuevo canal de notificaciones con un ID previamente almacenado en la configuración de la aplicación y el nombre obtenido del archivo strings.xml.

        //Añadiendo descripción al canal de notificaciones.

        //Habilitando y configurando LED de notificaciones.

        //Habilitando vibraciones.

        //Creando canal de notificaciones.
    }

}