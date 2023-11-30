/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class Horario {
    private int horarioId;
    private boolean estado;
    private String descripcion;
    private Time horaInicio;
    private Time horaFin;
       
   
    public static Horario obtenerHorarioPorDescripcion(Connection connection, String descripcion) throws SQLException {
        String sql = "SELECT * FROM horario WHERE descripcion = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, descripcion);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return construirHorarioDesdeResultSet(resultSet);
                }
            }
        }
        return null;
    }

    private static Horario construirHorarioDesdeResultSet(ResultSet resultSet) throws SQLException {
        Horario horario = new Horario();
        horario.setHorarioId(resultSet.getInt("horario_id"));
        horario.setEstado(resultSet.getBoolean("estado"));
        horario.setDescripcion(resultSet.getString("descripcion"));
        horario.setHoraInicio(resultSet.getTime("hora_inicio"));
        horario.setHoraFin(resultSet.getTime("hora_fin"));
        return horario;
    }
}
