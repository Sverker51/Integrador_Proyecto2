/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;

import com.utp.registrodeasistencia.controller.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import static sun.security.krb5.Config.duration;

/**
 *
 * @author Miguel
 */
@Data
public class Asistencia extends ConnectionDB{
    private int  asistenciaId;
    private String dni;
    private Timestamp fechaHoraIngreso;
    private Timestamp fechaHoraSalida;
    private int minutosTardanza;
    public long diferenciaTiempo;
    
    public long obtenerSegundosTardanza(String dni) throws SQLException, ClassNotFoundException {
        try {
            this.Conectar();

            // Obtener la hora de inicio del horario asociado al usuario
            String sqlHoraInicio = "SELECT hora_inicio FROM horario "
                    + "INNER JOIN detalle_horario_usuario ON horario.horario_id = detalle_horario_usuario.horario_id "
                    + "WHERE detalle_horario_usuario.dni = ?";

            ResultSet resultSetHoraInicio;
            Timestamp horaInicio = null;
            try (PreparedStatement preparedStatementHoraInicio = this.conexion.prepareStatement(sqlHoraInicio)) {
                preparedStatementHoraInicio.setString(1, dni);
                resultSetHoraInicio = preparedStatementHoraInicio.executeQuery();
                System.out.println("Dni del usuario: "+dni);
                horaInicio = null;
                
                if (resultSetHoraInicio.next()) {
                    horaInicio = resultSetHoraInicio.getTimestamp("hora_inicio");
                    System.out.println("Hora del inicio  "+horaInicio);
                    
                    
                    // Obtener la hora actual
                    Instant horaActual = Instant.now();
                    System.out.println(""+horaActual);
                    
                    // Obtener la fecha actual con la hora fija a las 08:00:00
                    LocalDate fechaActual = LocalDate.now();
                    LocalTime horaFijaInicio = LocalTime.of(8, 0, 0);
                    Instant horaInicioInstant = horaFijaInicio.atDate(fechaActual).atZone(ZoneId.systemDefault()).toInstant();
                    System.out.println("Hora de inicio ajustada: " + horaInicioInstant);

                    // Calcular la diferencia de tiempo
                    diferenciaTiempo = ChronoUnit.SECONDS.between(horaInicioInstant, horaActual);
                    System.out.println("Diferencia de Tiempo "+diferenciaTiempo);
                }     
            }

            if (horaInicio == null) {
                // No se encontró un horario asociado al usuario
                System.out.println("No se encontro un horario asociado");
                return 0;
            }
            //Retorna la diferencia en segundos
            return diferenciaTiempo;
            
            
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            } finally {
                this.Cerrar();
            }
            
        
    }
}