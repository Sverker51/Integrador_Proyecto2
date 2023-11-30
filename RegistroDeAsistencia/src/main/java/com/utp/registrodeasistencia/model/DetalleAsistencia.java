/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;

import com.utp.registrodeasistencia.controller.ConnectionDB;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class DetalleAsistencia {
    
    private String dni;
    private int horarioId;
    private Timestamp fechaHoraIngreso;
    private Timestamp fechaHoraSalida;
    private int minutosTardanza;
    private Usuario usuario;
    private Horario horario;

    public DetalleAsistencia(){
        
    }
    
    public void registrarEntrada() {
        Timestamp fechaHoraIngreso = new Timestamp(System.currentTimeMillis());

        ConnectionDB connection = null;
        PreparedStatement preparedStatement = null;
        
        
        try {
            
            connection = (ConnectionDB) DriverManager.getConnection("jdbc:mysql://url:3306/bd_asistencia", "root", "L1nux");

            // Preparar la consulta SQL para la inserción
            String sql = "INSERT INTO detalle_asistencia (dni, horario_id, fecha_hora_ingreso,fecha_hora_salida,minutos_tardanza) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, usuario.getDni());
            preparedStatement.setInt(2, horario.getHorarioId()); 
            preparedStatement.setTimestamp(3, fechaHoraIngreso);

            // Ejecutar la consulta
            int filasAfectadas = preparedStatement.executeUpdate();
            System.out.println("Filas afectadas: " + filasAfectadas);

            

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        } finally {
            // Cerrar recursos
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }

    private void calcularMinutosTardanza() {
        // Obtener la hora actual y la hora de inicio del horario
        long horaActualMillis = System.currentTimeMillis();
        long horaInicioMillis = this.horario.getHoraInicio().getTime();

        // Calcular la diferencia en minutos
        long diferenciaMillis = horaActualMillis - horaInicioMillis;
        this.minutosTardanza = (int) (diferenciaMillis / (60 * 1000));

        // Asegurarse de que los minutos de tardanza no sean negativos
        this.minutosTardanza = Math.max(0, this.minutosTardanza);
    }

    // Otros métodos y lógica según sea necesario
}

