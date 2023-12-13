/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import java.text.DecimalFormat;

/**
 *
 * @author CARLOS
 */
public class GenerarCorreoAprobacion {
    
    public void enviarCorreo(String destinatario, String fecha, String nombreDestinatario, String motivo, String mensaje) {

        DecimalFormat df = new DecimalFormat("#.00");
        String contenidoHTML = leerContenidoPlantilla();

        contenidoHTML = contenidoHTML.replace("[Nombre]", nombreDestinatario); // Reemplaza con el nombre del cliente
        contenidoHTML = contenidoHTML.replace("[Fecha]", fecha); // Reemplaza con el total general
        contenidoHTML = contenidoHTML.replace("[Motivo]", motivo);
        contenidoHTML = contenidoHTML.replace("[Mensaje]", mensaje);
        
        StringBuilder tablaHTML = new StringBuilder();

        enviarCorreoElectronico(destinatario, motivo, contenidoHTML);
    }

    private static void enviarCorreoElectronico(String destinatario, String asunto, String contenido) {
        EnviarMail enviarMail = new EnviarMail();
        enviarMail.enviarCorreo(destinatario, asunto, contenido);
    }

    private static String leerContenidoPlantilla() {
        StringBuilder contenido = new StringBuilder();

        contenido.append("<!DOCTYPE html>\n"
                + "<html lang=\"es\">\n"
                + "<head>\n"
                + "    <meta charset=\"UTF-8\">\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                + "    <title>Solicitud de Día Libre</title>\n"
                + "    <style>\n"
                + "        body {\n"
                + "            font-family: Arial, sans-serif;\n"
                + "            background-color: #f4f4f4;\n"
                + "            margin: 0;\n"
                + "            padding: 0;\n"
                + "        }\n"
                + "\n"
                + "        #notificacion {\n"
                + "            background-color: #fff;\n"
                + "            border-radius: 8px;\n"
                + "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n"
                + "            padding: 20px;\n"
                + "            width: 300px;\n"
                + "            text-align: center;\n"
                + "            margin: 50px auto;\n"
                + "        }\n"
                + "\n"
                + "        h2 {\n"
                + "            color: #4caf50;\n"
                + "        }\n"
                + "\n"
                + "        p {\n"
                + "            margin: 10px 0;\n"
                + "        }\n"
                + "    </style>\n"
                + "</head>\n"
                + "<body>\n"
                + "    <div id=\"notificacion\">\n"
                + "        <h2>Aceptación de Solicitud de Día Libre</h2>\n"
                + "        <p><strong>Nombre:</strong> [Nombre]</p>\n"
                + "        <p><strong>Fecha de solicitud:</strong> [Fecha]</p>\n"
                + "        <p><strong>Motivo:</strong> [Motivo]</p>\n"
                + "        <p><strong>[Mensaje]</p>\n"
                + "    </div>\n"
                + "</body>\n"
                + "</html>");

        return contenido.toString();
    }
}
