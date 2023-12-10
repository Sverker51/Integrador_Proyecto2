/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import com.utp.registrodeasistencia.model.DetalleAsistencia;
import com.utp.registrodeasistencia.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Sverker51
 */
public class DetalleAsistenciaDaoImple extends ConnectionDB{
    
    public List<DetalleAsistencia> buscarDetallesAsistencia(String nombre, LocalDate fecha) throws SQLException, ClassNotFoundException {
        List<DetalleAsistencia> detallesAsistencia = new ArrayList<>();

        try {
            this.Conectar();

            // Construye la consulta SQL basada en los filtros proporcionados
            String sql = "SELECT usuario.nombre, asistencia.fecha_hora_ingreso, asistencia.fecha_hora_salida, asistencia.minutos_tardanza "
                        + "FROM asistencia "
                        + "INNER JOIN usuario ON asistencia.dni = usuario.dni "
                        + "WHERE (? IS NULL OR usuario.nombre LIKE ?) AND (? IS NULL OR asistencia.fecha_hora_ingreso >= ? AND asistencia.fecha_hora_ingreso < ?)";
 
            try (PreparedStatement preparedStatement = this.conexion.prepareStatement(sql)) {
                // Configura los parámetros de la consulta
                preparedStatement.setString(1, nombre);
                preparedStatement.setString(2, "%" + nombre + "%");
                preparedStatement.setTimestamp(3, fecha != null ? Timestamp.valueOf(fecha.atStartOfDay()) : null);
                preparedStatement.setTimestamp(4, fecha != null ? Timestamp.valueOf(fecha.atStartOfDay()) : null);
                preparedStatement.setTimestamp(5, fecha != null ? Timestamp.valueOf(fecha.plusDays(1).atStartOfDay()) : null);

                // Ejecuta la consulta
                ResultSet resultSet = preparedStatement.executeQuery();

                // Procesa los resultados y construye la lista de DetalleAsistencia
                while (resultSet.next()) {
                    DetalleAsistencia detalle = new DetalleAsistencia();
                    Usuario usuario = new Usuario();
                    usuario.setNombre(resultSet.getString("nombre"));
                    detalle.setFechaHoraIngreso(resultSet.getTimestamp("fecha_hora_ingreso"));
                    detalle.setFechaHoraSalida(resultSet.getTimestamp("fecha_hora_salida"));
                    detalle.setMinutosTardanza(resultSet.getInt("minutos_tardanza"));

                    detallesAsistencia.add(detalle);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.Cerrar();
        }

        return detallesAsistencia;
    }
    
    
    

}
