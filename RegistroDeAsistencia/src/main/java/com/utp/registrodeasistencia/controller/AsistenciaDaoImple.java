/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import com.utp.registrodeasistencia.model.Asistencia;
import com.utp.registrodeasistencia.model.Horario;
import com.utp.registrodeasistencia.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsistenciaDaoImple extends ConnectionDB {

    private static final Logger logger = Logger.getLogger(AsistenciaDaoImple.class.getName());
    Usuario usuario = new Usuario();
    
    public void registrarEntrada(Usuario usuario) throws SQLException {
        try {
            this.Conectar();

            // Obtener la fecha y hora actual
            Timestamp fechaHoraIngreso = new Timestamp(System.currentTimeMillis());
            
            // Obtener el horario del usuario
            HorarioDaoImple horarioDao = new HorarioDaoImple();
            Horario horario = horarioDao.getHorarioById(usuario.getHorarioId());
            

            // Calcular los minutos de tardanza
            int minutosTardanza = calcularMinutosTardanza(horario.getHoraInicio(), fechaHoraIngreso);

            // Insertar la entrada en la tabla de asistencia
            String sql = "INSERT INTO asistencia (dni, fecha_hora_ingreso) VALUES (?, ?)";
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setTimestamp(2, fechaHoraIngreso);
            

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Entrada registrada exitosamente.");
                System.out.println("Minutos de tardanza: " + minutosTardanza);
            } else {
                System.out.println("No se pudo registrar la entrada.");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al registrar la entrada", e);
            e.printStackTrace();
        } finally {
            this.Cerrar();
             System.out.println("Recursos cerrados correctamente");
        }
    }
    
    
    public void registrarSalida(Usuario usuario) throws SQLException {
        try {
            this.Conectar();

            // Obtener la fecha y hora actual
            Timestamp fechaHoraSalida = new Timestamp(System.currentTimeMillis());
            
            // Obtener el horario del usuario
            HorarioDaoImple horarioDao = new HorarioDaoImple();
            Horario horario = horarioDao.getHorarioById(usuario.getHorarioId());
            

            // Calcular los minutos de tardanza
            int minutosTardanza = calcularMinutosTardanza(horario.getHoraInicio(), fechaHoraSalida);

            // Insertar la entrada en la tabla de asistencia
            
            String sql = "UPDATE asistencia SET (dni, fecha_hora_salida) VALUES (?, ?)";
            
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setTimestamp(2, fechaHoraSalida);
            

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Entrada registrada exitosamente.");
                System.out.println("Minutos de tardanza: " + minutosTardanza);
            } else {
                System.out.println("No se pudo registrar la entrada.");
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al registrar la entrada", e);
            e.printStackTrace();
        } finally {
            this.Cerrar();
             System.out.println("Recursos cerrados correctamente");
        }
    }
    
    
    private int calcularMinutosTardanza(Time horaInicio, Timestamp fechaHoraIngreso) {
        try {
            // Obtener la hora de inicio del horario en formato Time
            long horaInicioMillis = horaInicio.getTime();

            // Obtener la fecha y hora de ingreso en formato Time
            long fechaHoraIngresoMillis = fechaHoraIngreso.getTime();

            // Calcular la diferencia en minutos
            long diferenciaMillis = fechaHoraIngresoMillis - horaInicioMillis;
            return (int) (diferenciaMillis / (60 * 1000));

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al calcular los minutos de tardanza", e);
            return 0;
        }
    }

    // Otros métodos según sea necesario

    
}

