/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;


import com.utp.registrodeasistencia.model.Horario;
import com.utp.registrodeasistencia.model.Usuario;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AsistenciaDaoImple extends ConnectionDB {

    private static final Logger logger = Logger.getLogger(AsistenciaDaoImple.class.getName());

    public void registrarEntrada(Usuario usuario) throws SQLException, ClassNotFoundException {
        try {
            this.Conectar();

            // Obtener la fecha y hora actual
            Timestamp fechaHoraIngreso = new Timestamp(System.currentTimeMillis());

            // Insertar la entrada en la tabla de asistencia
            String sql = "INSERT INTO asistencia (dni, fecha_hora_ingreso) VALUES (?, ?)";
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setTimestamp(2, fechaHoraIngreso);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar la entrada", e);
            e.printStackTrace();
        } finally {
            this.Cerrar();
            System.out.println("Recursos cerrados correctamente");
        }
    }

    public void registrarSalida(Usuario usuario) throws SQLException, ClassNotFoundException {
        try {
            this.Conectar();

            // Obtener la fecha y hora actual
            Timestamp fechaHoraSalida = new Timestamp(System.currentTimeMillis());

            // Actualizar la salida en la tabla de asistencia
            String sql = "UPDATE asistencia SET fecha_hora_salida = ? WHERE dni = ? AND fecha_hora_salida IS NULL";
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setTimestamp(1, fechaHoraSalida);
            preparedStatement.setString(2, usuario.getDni());

            int filasAfectadas = preparedStatement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Salida registrada exitosamente.");
            } else {
                System.out.println("No se pudo registrar la salida.");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al registrar la salida", e);
            e.printStackTrace();
        } finally {
            this.Cerrar();
            System.out.println("Recursos cerrados correctamente");
        }
    }
}
    

